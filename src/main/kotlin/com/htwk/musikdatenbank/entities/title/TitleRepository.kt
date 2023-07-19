package com.htwk.musikdatenbank.entities.title

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface TitleRepository :
    CrudRepository<Title, Long>,
    PagingAndSortingRepository<Title, Long> {

    @Query(value = "SELECT * FROM title t WHERE MATCH(name) AGAINST (?1 IN BOOLEAN MODE) ORDER BY MATCH(t.name) AGAINST (?1 IN BOOLEAN MODE) ASC", nativeQuery = true)
    fun search(keyword: String): List<Title>

    @Query(
        value = "SELECT t.* " +
                "FROM title t " +
                "LEFT JOIN ( " +
                "    SELECT atl.title_id, COUNT(d.id) AS download_count " +
                "    FROM audio_title_link atl " +
                "    LEFT JOIN audio a ON atl.audio_id = a.id " +
                "    LEFT JOIN download d ON a.id = d.audio_id " +
                "    GROUP BY atl.title_id " +
                "    ORDER BY download_count DESC " +
                "    LIMIT 10 " +
                ") top_titles ON t.id = top_titles.title_id " +
                "ORDER BY top_titles.download_count DESC " +
                "LIMIT 10", nativeQuery = true
    )
    fun showMostPopular(): List<Title>
}
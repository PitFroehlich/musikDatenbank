package com.htwk.musikdatenbank.entities.title

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface TitleRepository :
    CrudRepository<Title, Long>,
    PagingAndSortingRepository<Title, Long> {

    @Query(value = "SELECT title.* " +
            "FROM (" +
            "    SELECT t.*, MATCH(t.name) AGAINST (?1 IN BOOLEAN MODE) AS relevance\n" +
            "    FROM title t" +
            "    WHERE MATCH(t.name) AGAINST (?1 IN BOOLEAN MODE)" +
            "    UNION\n" +
            "    SELECT t.*, MATCH(p.name) AGAINST (?1 IN BOOLEAN MODE) AS relevance\n" +
            "    FROM public_playlist_title_link ptl " +
            "    JOIN public_playlist p ON ptl.public_playlist_id = p.id " +
            "    JOIN title t ON ptl.title_id = t.id " +
            "    WHERE MATCH(p.name) AGAINST (?1 IN BOOLEAN MODE) " +
            "    UNION " +
            "    SELECT t.*, MATCH(p.name) AGAINST (?1 IN BOOLEAN MODE) AS relevance " +
            "    FROM private_playlist_title_link ptl " +
            "    JOIN private_playlist p ON ptl.private_playlist_id = p.id " +
            "    JOIN title t ON ptl.title_id = t.id " +
            "    WHERE MATCH(p.name) AGAINST (?1 IN BOOLEAN MODE) " +
            "    UNION " +
            "    SELECT t.*, MATCH(a.name) AGAINST (?1 IN BOOLEAN MODE) AS relevance " +
            "    FROM artist_title_link atl " +
            "    JOIN artist a ON atl.artist_id = a.id " +
            "    JOIN title t ON atl.title_id = t.id " +
            "    WHERE MATCH(a.name) AGAINST (?1 IN BOOLEAN MODE) " +
            "    UNION " +
            "    SELECT t.*, MATCH(al.name) AGAINST (?1 IN BOOLEAN MODE) AS relevance " +
            "    FROM album_title_link atl " +
            "    JOIN album al ON atl.album_id = al.id " +
            "    JOIN title t ON atl.title_id = t.id " +
            "    WHERE MATCH(al.name) AGAINST (?1 IN BOOLEAN MODE) " +
            ") ranked_titles " +
            "JOIN title ON ranked_titles.id = title.id " +
            "ORDER BY relevance DESC;", nativeQuery = true)
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
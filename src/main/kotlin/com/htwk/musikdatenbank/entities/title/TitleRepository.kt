package com.htwk.musikdatenbank.entities.title

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface TitleRepository:
    CrudRepository<Title, Long>,
    PagingAndSortingRepository<Title, Long>  {
    @Query(value = "SELECT * FROM title WHERE MATCH(name) "
            + "AGAINST (?1)", nativeQuery = true)
    fun search(keyword: String): List<Title>

}
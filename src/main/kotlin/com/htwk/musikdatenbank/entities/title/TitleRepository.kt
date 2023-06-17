package com.htwk.musikdatenbank.entities.title

import org.springframework.data.repository.CrudRepository

interface TitleRepository: CrudRepository<Title, Long>  {
}
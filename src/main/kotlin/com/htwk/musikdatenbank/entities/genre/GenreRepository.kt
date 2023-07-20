package com.htwk.musikdatenbank.entities.genre

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface GenreRepository: CrudRepository<Genre, Long> {
    fun findByName(name: String): Optional<Genre>
}
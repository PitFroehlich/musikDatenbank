package com.htwk.musikdatenbank.entities.artist

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ArtistRepository: CrudRepository<Artist, Long> {
    fun findByName(name: String): Optional<Artist>
}
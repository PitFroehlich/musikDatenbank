package com.htwk.musikdatenbank.entities.artist

import org.springframework.data.repository.CrudRepository

interface ArtistRepository: CrudRepository<Artist, Long> {
}
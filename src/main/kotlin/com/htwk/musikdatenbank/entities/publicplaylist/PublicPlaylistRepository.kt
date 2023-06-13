package com.htwk.musikdatenbank.entities.publicplaylist

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PublicPlaylistRepository: CrudRepository<PublicPlaylist, Long> {
}
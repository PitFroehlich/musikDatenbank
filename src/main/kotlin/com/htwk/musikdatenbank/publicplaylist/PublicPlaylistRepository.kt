package com.htwk.musikdatenbank.publicplaylist

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PublicPlaylistRepository: CrudRepository<PublicPlaylist, Long> {
}
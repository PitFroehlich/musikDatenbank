package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.user.Users
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PublicPlaylistRepository: CrudRepository<PublicPlaylist, Long> {
    fun findAllByLabel(label: Label): MutableIterable<PublicPlaylist>
}
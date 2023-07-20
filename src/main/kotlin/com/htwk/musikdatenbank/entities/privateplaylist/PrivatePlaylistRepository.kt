package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.Users
import org.springframework.data.repository.CrudRepository

interface PrivatePlaylistRepository: CrudRepository<PrivatePlaylist, Long> {
    fun findAllByUsers(users: Users): MutableIterable<PrivatePlaylist>
}
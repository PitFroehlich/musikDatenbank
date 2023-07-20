package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.Users
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PrivatePlaylistRepository : CrudRepository<PrivatePlaylist, Long> {
    fun findAllByUsers(users: Users): MutableIterable<PrivatePlaylist>

    @Modifying
    @Transactional
    @Query("insert into private_playlist_title_link ( private_playlist_id, title_id) VALUES (?1, ?2)" , nativeQuery = true)
    fun createPlaylist(playlistId: Long, titleId: Long)
}
package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.entities.label.Label
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PublicPlaylistRepository: CrudRepository<PublicPlaylist, Long> {
    fun findAllByLabel(label: Label): MutableIterable<PublicPlaylist>

    @Modifying
    @Transactional
    @Query("INSERT INTO public_playlist_title_link ( public_playlist_id, title_id) VALUES (?1, ?2)" , nativeQuery = true)
    fun createPlaylist(playlistId: Long, titleId: Long)
}
package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.PrivatePlaylistApi
import org.openapitools.model.PrivatePlaylistView
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RestController

@RestController
class PrivatePlaylistController(
    val musicService: MusicService,
): PrivatePlaylistApi {
    val converter: PrivatePlaylistConverter = Mappers.getMapper(PrivatePlaylistConverter::class.java)

    @PreAuthorize("hasRole('USER')")
    override fun getPrivatePlaylists(): ResponseEntity<List<PrivatePlaylistView>> {
        val privatePlaylists = musicService.getAllPrivatePlaylists().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(privatePlaylists)
    }
}
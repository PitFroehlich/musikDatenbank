package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.PublicPlaylistApi
import org.openapitools.model.PublicPlaylistView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PublicPlaylistController(
    val musicService: MusicService,
    val converter: PublicPlaylistConverter = Mappers.getMapper(
        PublicPlaylistConverter::class.java
    )
) : PublicPlaylistApi {

    @GetMapping("/publicPlaylist")
    override fun getPublicPlaylists(): ResponseEntity<List<PublicPlaylistView>> {
        val publicPlaylists = musicService.getAllPublicPlaylists().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(publicPlaylists)
    }
}
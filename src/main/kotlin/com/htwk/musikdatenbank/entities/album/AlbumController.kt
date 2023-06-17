package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AlbumApi
import org.openapitools.model.AlbumView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AlbumController(
    val musicService: MusicService,
    val converter: AlbumConverter = Mappers.getMapper(AlbumConverter::class.java)
): AlbumApi {
    override fun getAlbums(): ResponseEntity<List<AlbumView>> {
        val albums = musicService.getAllAlbums().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(albums)
    }
}
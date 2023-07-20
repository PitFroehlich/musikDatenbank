package com.htwk.musikdatenbank.entities.artist

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.ArtistApi
import org.openapitools.model.ArtistView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ArtistController (
    val musicService: MusicService,
): ArtistApi {
    val converter: ArtistConverter = Mappers.getMapper(ArtistConverter::class.java)
    override fun getArtists(): ResponseEntity<List<ArtistView>> {
        val artists = musicService.getAllArtists().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(artists)
    }
}

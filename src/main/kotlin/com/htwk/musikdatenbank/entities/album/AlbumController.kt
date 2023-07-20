package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AlbumApi
import org.openapitools.model.AlbumView
import org.openapitools.model.AudioView
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class AlbumController(
    val musicService: MusicService,
    val albumConverter: AlbumConverter = Mappers.getMapper(AlbumConverter::class.java)
): AlbumApi {
    override fun getAlbums(): ResponseEntity<List<AlbumView>> {
        val albums = musicService.getAllAlbums().map { albumConverter.toView(it) }.toList()

        return ResponseEntity.ok(albums)
    }

    override fun postAlbum(
        name: String,
        year: LocalDate,
        image: Resource,
        text: String,
        labelId: Int,
        presskitId: Int?
    ): ResponseEntity<AlbumView> {
        val createdAlbum = musicService.postAlbum(year, image, text, labelId, presskitId)
        return ResponseEntity.ok(albumConverter.toView(createdAlbum))
    }
}
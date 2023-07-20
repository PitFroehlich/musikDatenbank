package com.htwk.musikdatenbank.entities.genre

import com.htwk.musikdatenbank.entities.label.LabelConverter
import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.GenreApi
import org.openapitools.model.GenreView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GenreController(val musicService: MusicService): GenreApi {
    val converter: GenreConverter = Mappers.getMapper(
            GenreConverter::class.java
    )

    override fun getGenres(): ResponseEntity<List<GenreView>> {
        val genres = musicService.getAllGenres().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(genres)
    }
}
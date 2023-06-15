package com.htwk.musikdatenbank.entities.title

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.TitleApi
import org.openapitools.model.TitleView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TitleController(
    val musicService: MusicService,
    val converter: TitleConverter = Mappers.getMapper(TitleConverter::class.java)
) : TitleApi {
    @GetMapping("/titles")
    override fun getTitles(): ResponseEntity<List<TitleView>> {
        val titles = musicService.getAllTitles().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(titles)
    }
}



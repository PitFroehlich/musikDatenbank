package com.htwk.musikdatenbank.entities.title

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.TitleApi
import org.openapitools.model.TitleView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TitleController(
    val musicService: MusicService,
) : TitleApi {
    val converter: TitleConverter = Mappers.getMapper(TitleConverter::class.java)

    override fun getTitles(): ResponseEntity<List<TitleView>> {
        val titles = musicService.getAllTitles().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(titles)
    }

    override fun searchTitles(keyword: String?): ResponseEntity<List<TitleView>> {
        val titles = musicService.searchTitle(keyword).map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(titles)
    }

    @GetMapping("/hallo")
    fun hallo(): ResponseEntity<Unit> {
        return ResponseEntity.status(200).build()
    }
}



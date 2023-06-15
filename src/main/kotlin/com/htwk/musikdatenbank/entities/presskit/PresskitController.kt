package com.htwk.musikdatenbank.entities.presskit

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.PresskitApi
import org.openapitools.model.PresskitView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PresskitController(
    val musicService: MusicService,
    val converter: PresskitConverter = Mappers.getMapper(PresskitConverter::class.java)
): PresskitApi {
    override fun getPresskits(): ResponseEntity<List<PresskitView>> {
        val presskits = musicService.getAllPresskits().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(presskits)
    }
}
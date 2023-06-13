package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AudioApi
import org.openapitools.model.AudioView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AudioController(
    val musicService: MusicService,
    val converter: AudioConverter = Mappers.getMapper(AudioConverter::class.java)
) : AudioApi {

    override fun getAudios(): ResponseEntity<List<AudioView>> {
        val audios = musicService.getAllAudios().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(audios)
    }
}
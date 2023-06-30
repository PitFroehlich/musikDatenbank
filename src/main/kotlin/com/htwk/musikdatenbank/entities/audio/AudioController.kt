package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AudioApi
import org.openapitools.model.AudioDTO
import org.openapitools.model.AudioView
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class AudioController(
    val musicService: MusicService,
    val converter: AudioConverter = Mappers.getMapper(AudioConverter::class.java)
) : AudioApi {

    override fun getAudios(): ResponseEntity<List<AudioView>> {
        val audios = musicService.getAllAudios().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(audios)
    }
    //take params for method from request body
    override fun postAudio(labelId: Int, wav: Resource): ResponseEntity<AudioView> {
        //post with params
        val audio = musicService.postAudio(labelId, wav)
        //return complete object
        return ResponseEntity.ok(converter.convertToView(audio))
    }
}
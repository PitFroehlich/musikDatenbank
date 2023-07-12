package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AudioApi
import org.openapitools.api.AudioFileApi
import org.openapitools.model.AudioView
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AudioController(
    val musicService: MusicService,
    val converter: AudioConverter = Mappers.getMapper(AudioConverter::class.java)
) : AudioApi, AudioFileApi {

    override fun getAudios(): ResponseEntity<List<AudioView>> {
        val audios = musicService.getAllAudios().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(audios)
    }

    override fun getAudioFile(audioId: String): ResponseEntity<ByteArray> {
        val audioFile = musicService.getAudioFile(audioId)
        return createResponseEntity(audioFile, "download.wav")
    }

    override fun postAudio(labelId: Int, wav: Resource): ResponseEntity<AudioView> {
        val audio = musicService.postAudio(labelId, wav)
        return ResponseEntity.ok(converter.convertToView(audio))
    }

    private fun createResponseEntity(
        report: ByteArray,
        fileName: String
    ): ResponseEntity<ByteArray> =
        ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
            .body(report)
}
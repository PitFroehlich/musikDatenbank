package com.htwk.musikdatenbank.entities.mood

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.MoodApi
import org.openapitools.model.MoodView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MoodController(
    val musicService: MusicService,
    val converter: MoodConverter = Mappers.getMapper(MoodConverter::class.java)
) : MoodApi {

    override fun getMoods(): ResponseEntity<List<MoodView>> {
        val moods = musicService.getAllMoods().map {
            converter.convertToView(it)
        }.toList()

        return ResponseEntity.ok(moods)
    }

}
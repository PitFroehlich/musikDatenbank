package com.htwk.musikdatenbank.mood

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.MoodApiController
import org.openapitools.model.MoodView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MoodController(
    val musicService: MusicService,
    val converter: MoodConverter = Mappers.getMapper(MoodConverter::class.java)
) : MoodApiController() {

    @GetMapping("/mood")
    override fun getMoods(): ResponseEntity<List<MoodView>> {
        val moods = musicService.getAllMoods().map {
            converter.convertToView(it)
        }.toList()

        println(moods)
        println(musicService.getAllMoods().toList()[0])

        return ResponseEntity.ok(moods)
    }

}
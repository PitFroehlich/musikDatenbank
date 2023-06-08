package com.htwk.musikdatenbank.mood

import org.openapitools.api.MoodControllerApi
import org.openapitools.model.MoodView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MoodController(private val moodService: MoodService, private val moodMapper: MoodMapper): MoodControllerApi {
    @Override
    override fun getMood(): ResponseEntity<List<MoodView>> {
        var mood: List<Mood> = moodService.getMood();
        var view = moodMapper.toViewList(mood)
        return ResponseEntity.ok(view)
    }

}

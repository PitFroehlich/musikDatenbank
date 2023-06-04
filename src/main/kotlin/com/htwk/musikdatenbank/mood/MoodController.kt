package com.htwk.musikdatenbank.mood

import org.openapitools.api.MoodApiController
import org.openapitools.model.MoodView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MoodController(val moodService: MoodService) : MoodApiController() {

    @GetMapping("/mood")
    override fun getMood(): ResponseEntity<MoodView> {
        val mood = moodService.getMood()
        return ResponseEntity.ok(mood as MoodView) // TODO create casting method
    }

}
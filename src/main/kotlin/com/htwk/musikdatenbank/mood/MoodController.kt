package com.htwk.musikdatenbank.mood

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MoodController(val moodService: MoodService) {

    @GetMapping("/mood")
    fun getMood() = ResponseEntity.ok(moodService.getMood())

}
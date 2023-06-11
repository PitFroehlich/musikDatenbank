package com.htwk.musikdatenbank.entities.owner

import com.htwk.musikdatenbank.entities.mood.MoodConverter
import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.OwnerApiController
import org.openapitools.model.OwnerView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OwnerController(
    val musicService: MusicService,
    val converter: OwnerConverter = Mappers.getMapper(OwnerConverter::class.java)
) : OwnerApiController() {

    @GetMapping("/owner")
    override fun getOwners(): ResponseEntity<List<OwnerView>> {
        val owners = musicService.getAllOwners().map { converter.convertToView(it) }
        return ResponseEntity.ok(owners)
    }
}
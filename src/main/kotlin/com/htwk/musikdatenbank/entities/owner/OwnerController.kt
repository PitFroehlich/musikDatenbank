package com.htwk.musikdatenbank.entities.owner

import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.OwnerApi
import org.openapitools.model.OwnerView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OwnerController(
    val musicService: MusicService,
    val converter: OwnerConverter = Mappers.getMapper(OwnerConverter::class.java)
) : OwnerApi{

    override fun getOwners(): ResponseEntity<List<OwnerView>> {
        val owners = musicService.getAllOwners().map { converter.convertToView(it) }
        return ResponseEntity.ok(owners)
    }
}
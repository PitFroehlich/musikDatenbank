package com.htwk.musikdatenbank.owner

import com.htwk.musikdatenbank.services.music.MusicService
import org.openapitools.api.OwnerApiController
import org.openapitools.model.OwnerView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OwnerController(val musicService: MusicService): OwnerApiController() {

//    @GetMapping("/owner")
//    override fun getOwner(): ResponseEntity<OwnerView> {
//        return ResponseEntity.ok(ownerService.getAllOwners())
//    }
}
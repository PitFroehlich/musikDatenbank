package com.htwk.musikdatenbank.entities.user

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.UserApi
import org.openapitools.model.UserView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    val musicService: MusicService,
    val converter: UserConverter = Mappers.getMapper(UserConverter::class.java)
): UserApi {
    override fun getUsers(): ResponseEntity<List<UserView>> {
        val users = musicService.getAllUsers().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(users)
    }
}
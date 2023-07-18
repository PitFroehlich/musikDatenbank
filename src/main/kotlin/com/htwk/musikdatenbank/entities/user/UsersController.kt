package com.htwk.musikdatenbank.entities.user

import com.htwk.musikdatenbank.services.MusicService
import com.htwk.musikdatenbank.services.UserService
import org.mapstruct.factory.Mappers
import org.openapitools.api.LoginApi
import org.openapitools.api.UserApi
import org.openapitools.model.AuthenticationView
import org.openapitools.model.UserView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    val musicService: MusicService,
    val userService: UserService
): UserApi, LoginApi {
    val converter: UsersConverter = Mappers.getMapper(UsersConverter::class.java)
    override fun getUsers(): ResponseEntity<List<UserView>> {
        val users = musicService.getAllUsers().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(users)
    }

    override fun createUser(userView: UserView?): ResponseEntity<Unit> {
        if(userView == null) {
            return ResponseEntity.badRequest().build()
        }
        userService.createUser(userView)
        return ResponseEntity.status(201).build()
    }

    override fun login(authenticationView: AuthenticationView?): ResponseEntity<UserView> {
        if(authenticationView == null){
            return ResponseEntity.badRequest().build()
        }

        if(userService.loginUser(authenticationView)) {
            return ResponseEntity.status(401).build()
        }

        return ResponseEntity.ok(userService.getUser(authenticationView.username!!))
    }
}
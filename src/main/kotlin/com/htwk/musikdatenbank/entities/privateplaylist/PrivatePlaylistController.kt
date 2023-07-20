package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.UsersConverter
import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.PrivatePlaylistApi
import org.openapitools.model.PrivatePlaylistCreateDTO
import org.openapitools.model.PrivatePlaylistView
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RestController

@RestController
class PrivatePlaylistController(
        val musicService: MusicService,
) : PrivatePlaylistApi {
    val converter: PrivatePlaylistConverter = Mappers.getMapper(PrivatePlaylistConverter::class.java)
    val userConverter: UsersConverter = Mappers.getMapper(UsersConverter::class.java)

    @PreAuthorize("hasRole('USER')")
    override fun getPrivatePlaylists(username: String?): ResponseEntity<List<PrivatePlaylistView>> {
        if (username != null) {
            val privatePlaylists = musicService.getAllPrivatePlaylistsByUser(username).map { converter.convertToView(it) }.toList()
            return ResponseEntity.ok(privatePlaylists)
        }

        val privatePlaylists = musicService.getAllPrivatePlaylists().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(privatePlaylists)
    }

    @PreAuthorize("hasRole('USER')")
    override fun createPrivatePlaylist(privatePlaylistCreateDTO: PrivatePlaylistCreateDTO?): ResponseEntity<Unit> {
        if (privatePlaylistCreateDTO?.playlist == null || privatePlaylistCreateDTO.titles == null || privatePlaylistCreateDTO.titles!!.isEmpty() || privatePlaylistCreateDTO.playlist!!.user == null) {
            return ResponseEntity.badRequest().build()
        }

        val user = privatePlaylistCreateDTO.playlist!!.user?.let { userConverter.convertToEntity(it) }
                ?: return ResponseEntity.badRequest().build()

        val convertedPlaylist = converter.convertToEntity(privatePlaylistCreateDTO.playlist!!, user)

        musicService.createPrivatePlaylist(convertedPlaylist, privatePlaylistCreateDTO.titles!!.map { it.toLong() }.toList())
        return ResponseEntity.status(201).build()
    }
}
package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.entities.label.LabelConverter
import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.PublicPlaylistApi
import org.openapitools.model.PublicPlaylistCreateDTO
import org.openapitools.model.PublicPlaylistView
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RestController

@RestController
class PublicPlaylistController(
    val musicService: MusicService,
) : PublicPlaylistApi {
    val converter: PublicPlaylistConverter = Mappers.getMapper(
        PublicPlaylistConverter::class.java
    )
    val labelConverter: LabelConverter = Mappers.getMapper(LabelConverter::class.java)

    @PreAuthorize("hasRole('LABEL')")
    override fun getPublicPlaylists(labelId: Int?): ResponseEntity<List<PublicPlaylistView>> {
        if (labelId != null) {
            val publicPlaylists =
                musicService.getAllPublicPlaylistsByLabel(labelId.toLong()).map { converter.convertToView(it) }.toList()

            return ResponseEntity.ok(publicPlaylists)
        }

        val publicPlaylists = musicService.getAllPublicPlaylists().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(publicPlaylists)
    }

    @PreAuthorize("hasRole('LABEL')")
    override fun createPublicPlaylist(publicPlaylistCreateDTO: PublicPlaylistCreateDTO?): ResponseEntity<Unit> {
        if (publicPlaylistCreateDTO?.playlist == null
            || publicPlaylistCreateDTO?.titles.isNullOrEmpty()
            || publicPlaylistCreateDTO.playlist!!.label == null
        ) {
            return ResponseEntity.badRequest().build()
        }
        val label = publicPlaylistCreateDTO.playlist!!.label?.let { labelConverter.convertToEntity(it) }
            ?: return ResponseEntity.badRequest().build()

        val convertedPlaylist = converter.convertToEntity(publicPlaylistCreateDTO.playlist!!, label)

        musicService.createPublicPlaylist(convertedPlaylist, publicPlaylistCreateDTO.titles!!.map { it.toLong() }.toList())
        return ResponseEntity.status(201).build()
        }
    }
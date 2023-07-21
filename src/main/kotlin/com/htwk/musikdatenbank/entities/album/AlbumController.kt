package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.entities.title.TitleConverter
import com.htwk.musikdatenbank.services.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.AlbumApi
import org.openapitools.api.AlbumTitlesApi
import org.openapitools.model.AlbumView
import org.openapitools.model.TitleView
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class AlbumController(
    val musicService: MusicService,
    val albumConverter: AlbumConverter = Mappers.getMapper(AlbumConverter::class.java),
    val titleConverter: TitleConverter = Mappers.getMapper(TitleConverter::class.java)
): AlbumApi, AlbumTitlesApi {
    override fun getAlbums(): ResponseEntity<List<AlbumView>> {
        val albums = musicService.getAllAlbums().map { albumConverter.toView(it) }.toList()

        return ResponseEntity.ok(albums)
    }

    override fun postAlbum(
        name: String,
        year: LocalDate,
        image: Resource,
        text: String,
        labelId: Int,
        presskitId: Int,
    ): ResponseEntity<AlbumView> {
        val createdAlbum = musicService.postAlbum(
            name,
            year,
            image,
            text,
            labelId,
            presskitId)
        return ResponseEntity.ok(albumConverter.toView(createdAlbum))
    }

    override fun getAlbum(albumId: Int): ResponseEntity<AlbumView> {
        val album = musicService.getAlbum(albumId)
        return ResponseEntity.ok(albumConverter.toView(album))
    }

    override fun getAlbumTitles(albumId: Int): ResponseEntity<List<TitleView>> {
        val titleList = musicService.getAlbumTitles(albumId)
        return ResponseEntity.ok(titleConverter.toListView(titleList))
    }

    override fun addTitleToAlbum(titleId: Int, albumId: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(musicService.addTitleToAlbum(titleId, albumId))
    }

    override fun deleteTitleFromAlbum(albumTitleLinkId: Int): ResponseEntity<Unit> {
       return ResponseEntity.ok(musicService.deleteTitleFromAlbum(albumTitleLinkId))
    }



}
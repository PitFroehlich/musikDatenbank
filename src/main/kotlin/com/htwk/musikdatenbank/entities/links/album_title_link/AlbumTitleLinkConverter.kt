package com.htwk.musikdatenbank.entities.links.album_title_link

import com.htwk.musikdatenbank.entities.album.Album
import com.htwk.musikdatenbank.entities.title.Title
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface AlbumTitleLinkConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titleId", source = "title")
    @Mapping(target = "albumId", source = "album")
    fun toEntity(
        title: Title,
        album: Album
    ): AlbumTitleLink
}
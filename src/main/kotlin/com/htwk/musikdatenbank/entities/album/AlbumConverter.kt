package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.presskit.Presskit
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.AlbumView
import java.time.LocalDate

@Mapper
interface AlbumConverter {

    @Mapping(target = "labelId", source = "label")
    @Mapping(target = "presskitId", source = "presskit")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cover", source = "image")
    @Mapping(target = "releaseDate", source = "year")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "text", source = "text")
    fun toEntity(
        name: String,
        year: LocalDate,
        image: ByteArray,
        text: String,
        label: Label,
        presskit: Presskit
    ): Album

    fun toView(album: Album): AlbumView

}
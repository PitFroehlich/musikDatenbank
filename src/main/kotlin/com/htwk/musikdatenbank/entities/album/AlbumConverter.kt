package com.htwk.musikdatenbank.entities.album

import org.mapstruct.Mapper
import org.openapitools.model.AlbumView
import org.springframework.core.io.Resource
import java.time.LocalDate

@Mapper
interface AlbumConverter {
   fun toEntity(year: LocalDate,
                image: Resource,
                text: String,
                labelId: Int,
                presskitId: Int?): Album

   fun toView(album: Album): AlbumView
}
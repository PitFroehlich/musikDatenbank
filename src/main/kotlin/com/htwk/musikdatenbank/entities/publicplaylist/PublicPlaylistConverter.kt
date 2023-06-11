package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.helpers.Converter
import org.springframework.core.io.Resource
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.PublicPlaylistView
import java.sql.Blob

@Mapper
interface PublicPlaylistConverter: Converter<PublicPlaylistView, PublicPlaylist> {


    override fun convertToView(entity: PublicPlaylist): PublicPlaylistView

    fun map(value: Resource): Blob
}
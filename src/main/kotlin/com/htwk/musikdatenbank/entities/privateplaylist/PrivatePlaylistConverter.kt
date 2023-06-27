package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.User
import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.PrivatePlaylistView

@Mapper
interface PrivatePlaylistConverter {
    fun convertToView(entity: PrivatePlaylist): PrivatePlaylistView

    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    fun convertToEntity(view: PrivatePlaylistView, user: User): PrivatePlaylist
}

package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.Users
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.PrivatePlaylistView

@Mapper
interface PrivatePlaylistConverter {
    @Mapping(target = "user", source = "users")
    fun convertToView(entity: PrivatePlaylist): PrivatePlaylistView

    @Mapping(target = "users", source = "users")
//    @Mapping(target = "username", ignore = true)
    @Mapping(target = "name", ignore = true)
    fun convertToEntity(view: PrivatePlaylistView, users: Users): PrivatePlaylist
}

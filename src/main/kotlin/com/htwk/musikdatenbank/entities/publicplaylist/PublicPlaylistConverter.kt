package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.entities.label.Label
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.PublicPlaylistView

// NOTE: There is an issue with Kotlin and interfaces and the @Named annotation, that's why we use abstract classes here
@Mapper
interface PublicPlaylistConverter {
    @Mapping(target = "label", source = "label")
    fun convertToView(entity: PublicPlaylist): PublicPlaylistView

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "view.name")
    @Mapping(target = "label", source = "label")
    fun convertToEntity(view: PublicPlaylistView, label: Label): PublicPlaylist
}
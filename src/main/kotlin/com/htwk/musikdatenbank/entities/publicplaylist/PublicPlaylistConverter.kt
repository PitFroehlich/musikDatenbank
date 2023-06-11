package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.helpers.Converter
import org.springframework.core.io.Resource
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.openapitools.model.PublicPlaylistView
import org.springframework.core.io.ByteArrayResource

// NOTE: There is an issue with Kotlin and interfaces and the @Named annotation, that's why we use abstract classes here
@Mapper
abstract class PublicPlaylistConverter : Converter<PublicPlaylistView, PublicPlaylist> {
    @Mapping(source = "previewPicture", target = "previewPicture", qualifiedByName = ["ByteToResource"])
    abstract override fun convertToView(entity: PublicPlaylist): PublicPlaylistView

    @Mapping(source = "previewPicture", target = "previewPicture", qualifiedByName = ["ResourceToByte"])
    abstract override fun convertToEntity(view: PublicPlaylistView): PublicPlaylist
    @Named("ResourceToByte")
    fun resourceToByte(resource: Resource): ByteArray = resource.contentAsByteArray

    @Named("ByteToResource")
    fun byteToResource(bytes: ByteArray): Resource = ByteArrayResource(bytes)
}
package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.AlbumView

@Mapper
interface AlbumConverter: Converter<AlbumView, Album>
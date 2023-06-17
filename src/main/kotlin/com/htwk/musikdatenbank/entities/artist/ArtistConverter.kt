package com.htwk.musikdatenbank.entities.artist

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.ArtistView

@Mapper
interface ArtistConverter: Converter<ArtistView, Artist>
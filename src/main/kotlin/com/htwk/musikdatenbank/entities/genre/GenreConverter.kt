package com.htwk.musikdatenbank.entities.genre

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.GenreView

@Mapper
interface GenreConverter: Converter<GenreView, Genre>
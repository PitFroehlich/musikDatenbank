package com.htwk.musikdatenbank.entities.title

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.TitleView

@Mapper
interface TitleConverter: Converter<TitleView, Title>
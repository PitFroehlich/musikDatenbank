package com.htwk.musikdatenbank.entities.presskit

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.PresskitView

@Mapper
interface PresskitConverter: Converter<PresskitView, Presskit>
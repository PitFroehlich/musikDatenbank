package com.htwk.musikdatenbank.entities.mood

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.MoodView

@Mapper
interface MoodConverter: Converter<MoodView, Mood>
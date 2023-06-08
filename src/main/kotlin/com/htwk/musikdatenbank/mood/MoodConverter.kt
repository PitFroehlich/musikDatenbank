package com.htwk.musikdatenbank.mood

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.openapitools.model.MoodView

@Mapper
interface MoodConverter {
    fun convertToEntity(moodView: MoodView): Mood

    @Mapping(source = "id", target = "id")
    fun convertToView(mood: Mood): MoodView
}
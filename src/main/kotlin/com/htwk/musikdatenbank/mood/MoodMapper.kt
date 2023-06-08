package com.htwk.musikdatenbank.mood

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.MoodView

@Mapper(componentModel = "spring")
interface MoodMapper {
    fun toView(mood: Mood): MoodView

    fun toViewList(moods: List<Mood>): List<MoodView>
}
package com.htwk.musikdatenbank.entities.title

import com.htwk.musikdatenbank.entities.label.Label
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.TitleView

@Mapper
interface TitleConverter {
     @Mapping(target = "label", source = "label")
     @Mapping(target = "id", ignore = true)
     @Mapping(target = "name", ignore = true)
     fun convertToEntity(view: TitleView, label: Label): Title

     fun convertToView(entity: Title): TitleView

     fun toListView(entity: List<Title>): List<TitleView>
}
package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.entities.label.Label
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.AudioView

@Mapper
interface AudioConverter {
    fun convertToView(entity: Audio): AudioView

    @Mapping(target = "label", source = "label")
    @Mapping(target = "id", ignore = true)
    fun convertToEntity(view: AudioView, label: Label): Audio
}
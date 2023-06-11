package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.AudioView

@Mapper
interface AudioConverter: Converter<AudioView, Audio>
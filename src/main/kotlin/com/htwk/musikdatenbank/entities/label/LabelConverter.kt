package com.htwk.musikdatenbank.entities.label

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.LabelView

@Mapper
interface LabelConverter: Converter<LabelView, Label>
package com.htwk.musikdatenbank.entities.instrument

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.InstrumentView

@Mapper
interface InstrumentConverter: Converter<InstrumentView, Instrument>
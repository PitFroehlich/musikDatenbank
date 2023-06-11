package com.htwk.musikdatenbank.entities.instrument

import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.InstrumentApiController
import org.openapitools.model.InstrumentView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InstrumentController(
    val musicService: MusicService,
    val converter: InstrumentConverter = Mappers.getMapper(InstrumentConverter::class.java)
) : InstrumentApiController() {

    @GetMapping("/instrument")
    override fun getInstruments(): ResponseEntity<List<InstrumentView>> {
        val instruments = musicService.getAllInstruments().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(instruments)
    }
}
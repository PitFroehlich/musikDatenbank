package com.htwk.musikdatenbank.instrument

import com.htwk.musikdatenbank.services.music.MusicService
import org.springframework.web.bind.annotation.RestController

@RestController
class InstrumentController(val musicService: MusicService) {
}
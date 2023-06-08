package com.htwk.musikdatenbank.publicplaylist

import com.htwk.musikdatenbank.services.music.MusicService
import org.springframework.web.bind.annotation.RestController

@RestController
class PublicPlaylistController(val musicService: MusicService) {
}
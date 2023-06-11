package com.htwk.musikdatenbank.services.music

import com.htwk.musikdatenbank.entities.audio.Audio
import com.htwk.musikdatenbank.entities.audio.AudioRepository
import com.htwk.musikdatenbank.entities.instrument.Instrument
import com.htwk.musikdatenbank.entities.instrument.InstrumentRepository
import com.htwk.musikdatenbank.entities.mood.Mood
import com.htwk.musikdatenbank.entities.mood.MoodRepository
import com.htwk.musikdatenbank.entities.owner.Owner
import com.htwk.musikdatenbank.entities.owner.OwnerRepository
import com.htwk.musikdatenbank.entities.publicplaylist.PublicPlaylist
import com.htwk.musikdatenbank.entities.publicplaylist.PublicPlaylistRepository
import org.springframework.stereotype.Service

@Service
class MusicService(
    val moodRepository: MoodRepository,
    val ownerRepository: OwnerRepository,
    val instrumentRepository: InstrumentRepository,
    val publicPlaylistRepository: PublicPlaylistRepository,
    val audioRepository: AudioRepository
) {
    /*------------------------------------Mood------------------------------------------------*/
    fun getAllMoods(): MutableIterable<Mood> = moodRepository.findAll()

    /*------------------------------------Owner-----------------------------------------------*/
    fun getAllOwners(): MutableIterable<Owner> = ownerRepository.findAll()

    /*------------------------------------Instrument------------------------------------------*/
    fun getAllInstruments(): MutableIterable<Instrument> = instrumentRepository.findAll()

    /*----------------------------------Public Playlist---------------------------------------*/
    fun getAllPublicPlaylists(): MutableIterable<PublicPlaylist> = publicPlaylistRepository.findAll()

    /*--------------------------------------------Audio---------------------------------------*/
    fun getAllAudios(): MutableIterable<Audio> = audioRepository.findAll()

}
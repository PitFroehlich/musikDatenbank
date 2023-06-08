package com.htwk.musikdatenbank.services.music

import com.htwk.musikdatenbank.instrument.Instrument
import com.htwk.musikdatenbank.instrument.InstrumentRepository
import com.htwk.musikdatenbank.mood.Mood
import com.htwk.musikdatenbank.mood.MoodRepository
import com.htwk.musikdatenbank.owner.Owner
import com.htwk.musikdatenbank.owner.OwnerRepository
import com.htwk.musikdatenbank.publicplaylist.PublicPlaylist
import com.htwk.musikdatenbank.publicplaylist.PublicPlaylistRepository
import org.springframework.stereotype.Service

@Service
class MusicService(
    val moodRepository: MoodRepository,
    val ownerRepository: OwnerRepository,
    val instrumentRepository: InstrumentRepository,
    val publicPlaylistRepository: PublicPlaylistRepository
) {
    /*------------------------------------Mood------------------------------------------------*/
    fun getAllMoods(): MutableIterable<Mood> = moodRepository.findAll()

    /*------------------------------------Owner-----------------------------------------------*/
    fun getAllOwners(): MutableIterable<Owner> = ownerRepository.findAll()

    /*------------------------------------Instrument------------------------------------------*/
    fun getAllInstruments(): MutableIterable<Instrument> = instrumentRepository.findAll()

    /*----------------------------------Public Playlist---------------------------------------*/
    fun getAllPublicPlaylists(): MutableIterable<PublicPlaylist> = publicPlaylistRepository.findAll()

}
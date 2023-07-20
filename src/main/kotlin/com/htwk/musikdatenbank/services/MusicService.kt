package com.htwk.musikdatenbank.services

import com.htwk.musikdatenbank.entities.album.Album
import com.htwk.musikdatenbank.entities.album.AlbumRepository
import com.htwk.musikdatenbank.entities.artist.Artist
import com.htwk.musikdatenbank.entities.artist.ArtistRepository
import com.htwk.musikdatenbank.entities.audio.Audio
import com.htwk.musikdatenbank.entities.audio.AudioConverter
import com.htwk.musikdatenbank.entities.audio.AudioRepository
import com.htwk.musikdatenbank.entities.instrument.Instrument
import com.htwk.musikdatenbank.entities.instrument.InstrumentRepository
import com.htwk.musikdatenbank.entities.label.LabelRepository
import com.htwk.musikdatenbank.entities.mood.Mood
import com.htwk.musikdatenbank.entities.mood.MoodRepository
import com.htwk.musikdatenbank.entities.owner.Owner
import com.htwk.musikdatenbank.entities.owner.OwnerRepository
import com.htwk.musikdatenbank.entities.presskit.Presskit
import com.htwk.musikdatenbank.entities.presskit.PresskitRepository
import com.htwk.musikdatenbank.entities.privateplaylist.PrivatePlaylist
import com.htwk.musikdatenbank.entities.privateplaylist.PrivatePlaylistRepository
import com.htwk.musikdatenbank.entities.publicplaylist.PublicPlaylist
import com.htwk.musikdatenbank.entities.publicplaylist.PublicPlaylistRepository
import com.htwk.musikdatenbank.entities.title.Title
import com.htwk.musikdatenbank.entities.title.TitleRepository
import com.htwk.musikdatenbank.entities.user.Users
import com.htwk.musikdatenbank.entities.user.UsersRepository
import org.mapstruct.factory.Mappers
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class MusicService(
    val moodRepository: MoodRepository,
    val ownerRepository: OwnerRepository,
    val instrumentRepository: InstrumentRepository,
    val publicPlaylistRepository: PublicPlaylistRepository,
    val audioRepository: AudioRepository,
    private val titleRepository: TitleRepository,
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository,
    private val presskitRepository: PresskitRepository,
    private val privatePlaylistRepository: PrivatePlaylistRepository,
    private val usersRepository: UsersRepository,
    private val labelRepository: LabelRepository,

    ) {
    val audioConverter: AudioConverter = Mappers.getMapper(AudioConverter::class.java)

    /*--------------------------------------------Album---------------------------------------*/
    fun getAllAlbums(): MutableIterable<Album> = albumRepository.findAll()

    /*--------------------------------------------Artist---------------------------------------*/
    fun getAllArtists(): MutableIterable<Artist> = artistRepository.findAll()

    /*------------------------------------Mood------------------------------------------------*/
    fun getAllMoods(): MutableIterable<Mood> = moodRepository.findAll()

    /*------------------------------------Owner-----------------------------------------------*/
    fun getAllOwners(): MutableIterable<Owner> = ownerRepository.findAll()

    /*------------------------------------Instrument------------------------------------------*/
    fun getAllInstruments(): MutableIterable<Instrument> = instrumentRepository.findAll()

    /*------------------------------------Private Playlist------------------------------------*/
    fun getAllPrivatePlaylists(): MutableIterable<PrivatePlaylist> = privatePlaylistRepository.findAll()

    fun getAllPrivatePlaylistsByUser(username: String): MutableIterable<PrivatePlaylist> {
        val user = this.usersRepository.findByUsername(username)

        return this.privatePlaylistRepository.findAllByUsers(user.get())
    }

    /*------------------------------------Presskit--------------------------------------------*/
    fun getAllPresskits(): MutableIterable<Presskit> = presskitRepository.findAll()

    /*----------------------------------Public Playlist---------------------------------------*/
    fun getAllPublicPlaylists(): MutableIterable<PublicPlaylist> = publicPlaylistRepository.findAll()

    fun getAllPublicPlaylistsByLabel(labelId: Long): MutableIterable<PublicPlaylist> {
        val label = this.labelRepository.findById(labelId)

        return publicPlaylistRepository.findAllByLabel(label.get())
    }

    /*--------------------------------------------Audio---------------------------------------*/
    fun getAllAudios(): MutableIterable<Audio> = audioRepository.findAll()

    fun getAudioFile(audioId: String): ByteArray {
        val audio = audioRepository.findById(audioId.toLong())
        return audio.get().wav
    }

    fun postAudio(labelId: Int, wav: Resource): Audio {
        val byteArray = wav.contentAsByteArray
        val label = labelRepository.findById(labelId.toLong()).orElseThrow()
        return audioRepository.save(audioConverter.convertToAudio(label, byteArray))
    }

    /*--------------------------------------------Title---------------------------------------*/
    fun getAllTitles(): MutableIterable<Title> = titleRepository.findAll()

    fun searchTitle(
        keyword: String?,
        tempo: Int?,
        mood: Int?,
        genre: Int?,
        instrument: Int?
    ): Iterable<Title> {
        if (
            keyword == null
            && mood == null
            && genre == null
            && instrument == null
        ) {
            print(">>>> We are in the most popular $keyword $mood $genre $instrument")
            return titleRepository.showMostPopular()
        } else {
            if (keyword.isNullOrEmpty()) {
                print(">>>>>> We are in the Tagsearch $tempo $mood $genre $instrument")
                val moodLong = mood?.toLong()
                val genreLong = genre?.toLong()
                val instrumentLong = instrument?.toLong()
                val resultAND = titleRepository.tagSearchAND(tempo, moodLong, genreLong, instrumentLong)
                if (resultAND.isNullOrEmpty()) {
                    return titleRepository.tagSearchOR(tempo, moodLong, genreLong, instrumentLong)
                } else {
                    return resultAND
                }
            } else {
                if (mood == null
                    && genre == null
                    && instrument == null
                ) {
                    print(">>>>>> We are in the Fulltext Search")

                    val keywordReplaced = keyword.replace(Regex("\\s{2,}"), " ")
                    val keywordTrimmed = keywordReplaced.trimEnd()
                    val keywordSeperated = keywordTrimmed.split(" ").toTypedArray()
                    val searchStringAND = keywordSeperated.joinToString(" ") { "+$it" }
                    val searchStringANDResult = titleRepository.search(searchStringAND)
                    if (searchStringANDResult.isNullOrEmpty()) {
                        val searchStringOR = keywordSeperated.joinToString(" ")
                        return titleRepository.search(searchStringOR)
                    } else {
                        return searchStringANDResult
                    }
                } else {
                    print(">>>>>> We are in the Fallback Search")

                    return titleRepository.findAll()

                    //titleRepository.search(keyword, tempo, mood, genre, instrument)

                }
            }
        }
    }

    /*--------------------------------------------User----------------------------------------*/
    fun getAllUsers(): MutableIterable<Users> = usersRepository.findAll()


}
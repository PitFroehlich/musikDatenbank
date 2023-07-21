package com.htwk.musikdatenbank.services

import com.htwk.musikdatenbank.entities.album.Album
import com.htwk.musikdatenbank.entities.album.AlbumConverter
import com.htwk.musikdatenbank.entities.album.AlbumRepository
import com.htwk.musikdatenbank.entities.artist.Artist
import com.htwk.musikdatenbank.entities.artist.ArtistRepository
import com.htwk.musikdatenbank.entities.audio.Audio
import com.htwk.musikdatenbank.entities.audio.AudioConverter
import com.htwk.musikdatenbank.entities.audio.AudioRepository
import com.htwk.musikdatenbank.entities.genre.Genre
import com.htwk.musikdatenbank.entities.genre.GenreRepository
import com.htwk.musikdatenbank.entities.instrument.Instrument
import com.htwk.musikdatenbank.entities.instrument.InstrumentRepository
import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.label.LabelRepository
import com.htwk.musikdatenbank.entities.links.album_title_link.AlbumTitleLinkConverter
import com.htwk.musikdatenbank.entities.links.album_title_link.AlbumTitleLinkRepository
import com.htwk.musikdatenbank.entities.links.artist_title_link.ArtistTitleLink
import com.htwk.musikdatenbank.entities.links.artist_title_link.ArtistTitleLinkRepository
import com.htwk.musikdatenbank.entities.links.genre_title_link.GenreTitleLink
import com.htwk.musikdatenbank.entities.links.genre_title_link.GenreTitleLinkRepository
import com.htwk.musikdatenbank.entities.links.instrument_title_link.InstrumentTitleLink
import com.htwk.musikdatenbank.entities.links.instrument_title_link.InstrumentTitleLinkRepository
import com.htwk.musikdatenbank.entities.links.mood_title_link.MoodTitleLink
import com.htwk.musikdatenbank.entities.links.mood_title_link.MoodTitleLinkRepository
import com.htwk.musikdatenbank.entities.mood.Mood
import com.htwk.musikdatenbank.entities.mood.MoodRepository
import com.htwk.musikdatenbank.entities.owner.Owner
import com.htwk.musikdatenbank.entities.owner.OwnerRepository
import com.htwk.musikdatenbank.entities.presskit.Presskit
import com.htwk.musikdatenbank.entities.presskit.PresskitConverter
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
import org.openapitools.model.PresskitView
import org.openapitools.model.TitleUploadDto
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.URLConnection
import java.sql.Date
import java.time.LocalDate

@Service
class MusicService(
    val moodRepository: MoodRepository,
    val ownerRepository: OwnerRepository,
    val instrumentRepository: InstrumentRepository,
    val publicPlaylistRepository: PublicPlaylistRepository,
    val audioRepository: AudioRepository,
    val moodTitleLinkRepository: MoodTitleLinkRepository,
    val artistTitleLinkRepository: ArtistTitleLinkRepository,
    val genreTitleLinkRepository: GenreTitleLinkRepository,
    val instrumentTitleLinkRepository: InstrumentTitleLinkRepository,
    private val genreRepository: GenreRepository,
    private val titleRepository: TitleRepository,
    private val artistRepository: ArtistRepository,
    private val albumRepository: AlbumRepository,
    private val presskitRepository: PresskitRepository,
    private val privatePlaylistRepository: PrivatePlaylistRepository,
    private val usersRepository: UsersRepository,
    private val labelRepository: LabelRepository,
    private val albumTitleLinkRepository: AlbumTitleLinkRepository

    ) {
    val audioConverter: AudioConverter = Mappers.getMapper(AudioConverter::class.java)
    val pressKitConverter: PresskitConverter = Mappers.getMapper(PresskitConverter::class.java)
    val albumConverter: AlbumConverter = Mappers.getMapper(AlbumConverter::class.java)
    val albumTitleLinkConverter: AlbumTitleLinkConverter = Mappers.getMapper(AlbumTitleLinkConverter::class.java)
    /*--------------------------------------------Album---------------------------------------*/
    fun getAllAlbums(): MutableIterable<Album> = albumRepository.findAll()

    fun getAlbum(albumId: Int): Album {
        return albumRepository.findById(albumId.toLong()).orElseThrow()
    }

    fun getAlbumTitles(albumId: Int): List<Title> {
        val titleListe = titleRepository.findTitlesFromAlbum(albumId.toLong())
        return titleListe
    }

    fun addTitleToAlbum(titleId: Int, albumId: Int) {
        albumTitleLinkRepository.addTitleToAlbum(titleId.toLong(), albumId.toLong())
    }

    fun deleteTitleFromAlbum(albumTitleLinkId: Int) {
        albumTitleLinkRepository.deleteTitleFromAlbum(albumTitleLinkId)
    }

    fun postAlbum(
        name: String,
        year: LocalDate,
        image: Resource,
        text: String,
        labelId: Int,
        presskitId: Int,
    ): Album {
            val label = labelRepository.findById(labelId.toLong()).orElseThrow()
            val presskit = presskitRepository.findById(presskitId.toLong()).orElseThrow()
            val cover = image.contentAsByteArray
            return albumRepository.save(albumConverter.toEntity(name, year, cover, text, label, presskit))
        }



    /*--------------------------------------------Artist---------------------------------------*/
    fun getAllArtists(): MutableIterable<Artist> = artistRepository.findAll()

    /*------------------------------------Mood------------------------------------------------*/
    fun getAllMoods(): MutableIterable<Mood> = moodRepository.findAll()

    /*------------------------------------Genre-----------------------------------------------*/

    fun getAllGenres(): MutableIterable<Genre> = genreRepository.findAll()

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

    fun getUserFromPlaylist(playlistId: Long): Users {
        val playlist = this.privatePlaylistRepository.findById(playlistId)
        return playlist.get().users
    }

    fun createPrivatePlaylist(playlist: PrivatePlaylist, titles: List<Long>) {
        this.privatePlaylistRepository.save(playlist)

        titles.forEach { this.privatePlaylistRepository.createPlaylist(playlist.id, it) }
    }

    /*------------------------------------Presskit--------------------------------------------*/
    fun getAllPresskits(): MutableIterable<Presskit> = presskitRepository.findAll()

    private fun checkContentType(bytes: ByteArray, expectedContentType: String): Boolean {
        print(bytes.size)
        val inputStream: InputStream = ByteArrayInputStream(bytes)
        val mimeType: String = URLConnection.guessContentTypeFromStream(inputStream)
        return mimeType == expectedContentType
    }

    fun postPresskit(image: ByteArray?, pdf: ByteArray?): PresskitView {

//        if (image != null && pdf != null) {
//            if (Files.probeContentType(Path.of(image.toString())) == "image/jpeg"
//                && Files.probeContentType(Path.of(pdf.toString())) == "application/pdf"
//            ) {
        if (image == null || pdf == null) {
            throw Exception("Input is null")
        }
        /*        if (!checkContentType(image, "image/jpeg")) {
                    throw Exception("Wrong Image Format")
                }*/
        /*if (!checkContentType(pdf, "image/jpeg")) {
            throw Exception("PDF RIP")
        }*/
        //
        val presskit =
            presskitRepository.save(pressKitConverter.toEntity(image, pdf))
        return pressKitConverter.toView(presskit)
//            } else throw Exception("Wrong File Format")


//        } else throw Exception("Files not complete")
    }

    /*----------------------------------Public Playlist---------------------------------------*/
    fun getAllPublicPlaylists(): MutableIterable<PublicPlaylist> = publicPlaylistRepository.findAll()

    fun getAllPublicPlaylistsByLabel(labelId: Long): MutableIterable<PublicPlaylist> {
        val label = this.labelRepository.findById(labelId)
        if (labelRepository.findById(labelId).isPresent) {
            return publicPlaylistRepository.findAllByLabel(label.get())
        } else {
            throw Exception("There is no Label with this ID")
        }

    }

    fun createPublicPlaylist(playlist: PublicPlaylist, titles: List<Long>) {
        this.publicPlaylistRepository.save(playlist)

        titles.forEach { this.publicPlaylistRepository.createPlaylist(playlist.id, it) }
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

    fun uploadTitle(titleUploadDto: TitleUploadDto) {
        val label = labelRepository.findById(titleUploadDto.labelId.toLong())

        val title = Title(
            name = titleUploadDto.name,
            bpm = titleUploadDto.bpm,
            cover = titleUploadDto.cover,
            gemaNr = titleUploadDto.gemaNr,
            releaseDate = Date.valueOf(titleUploadDto.releaseDate),
            visible = titleUploadDto.visible,
            label = label.get(),
            id = null
        )
        val storedTitle = this.titleRepository.save(title)


        // Check all tags like moods, artists, instruments and genres
        // Then check if those already exist (by checking their names in the regarding tables) and either add them or only create a link to the existing one with the title
        if (storedTitle.id != null) {
            titleUploadDto.moods.forEach {
                val mood = moodRepository.findByName(it)
                if (mood.isPresent && mood.get().id != null) {
                    moodTitleLinkRepository.save(
                        MoodTitleLink(
                            moodId = mood.get().id!!,
                            titleId = storedTitle.id,
                            id = null
                        )
                    )
                } else {
                    val newMood = moodRepository.save(Mood(id = null, name = it))
                    moodTitleLinkRepository.save(
                        MoodTitleLink(
                            moodId = newMood.id!!,
                            titleId = storedTitle.id,
                            id = null
                        )
                    )
                }
            }

            titleUploadDto.artists.forEach {
                val artist = artistRepository.findByName(it)

                if (artist.isPresent && artist.get().id != null) {
                    artistTitleLinkRepository.save(
                        ArtistTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            artistId = artist.get().id!!
                        )
                    )
                } else {
                    val newArtist =
                        artistRepository.save(Artist(id = null, name = it, image = ByteArray(0), biography = ""))
                    artistTitleLinkRepository.save(
                        ArtistTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            artistId = newArtist.id!!
                        )
                    )
                }
            }

            titleUploadDto.genres.forEach {
                val genre = genreRepository.findByName(it)
                if (genre.isPresent && genre.get().id != null) {
                    genreTitleLinkRepository.save(
                        GenreTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            genreId = genre.get().id!!
                        )
                    )
                } else {
                    val newGenre = genreRepository.save(Genre(id = null, name = it))
                    genreTitleLinkRepository.save(
                        GenreTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            genreId = newGenre.id!!
                        )
                    )
                }
            }

            titleUploadDto.instruments.forEach {
                val instrument = instrumentRepository.findByName(it)
                if (instrument.isPresent && instrument.get().id != null) {
                    instrumentTitleLinkRepository.save(
                        InstrumentTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            instrumentId = instrument.get().id!!
                        )
                    )
                } else {
                    val newInstrument = instrumentRepository.save(Instrument(id = null, name = it))
                    instrumentTitleLinkRepository.save(
                        InstrumentTitleLink(
                            id = null,
                            titleId = storedTitle.id,
                            instrumentId = newInstrument.id!!
                        )
                    )
                }
            }
        }
    }

    fun getLabelFromTitle(titleId: Long): Label {
        val title = this.titleRepository.findById(titleId)

        return title.get().label
    }

    fun searchTitle(
        keyword: String?,
        tempo: Int?,
        mood: Int?,
        genre: Int?,
        instrument: Int?
    ): Iterable<Title> {
        if (
            keyword.isNullOrEmpty()
            && mood == null
            && genre == null
            && instrument == null
        ) {
            return titleRepository.showMostPopular()
        } else {
            if (keyword.isNullOrEmpty()) {
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
                    val searchStringAND = searchStringAndCreator(keyword)
                    val searchStringANDResult = titleRepository.search(searchStringAND)
                    if (searchStringANDResult.isNullOrEmpty()) {
                        val searchStringOR = searchStringOrCreator(keyword)
                        return titleRepository.search(searchStringOR)
                    } else {
                        return searchStringANDResult
                    }
                } else {
                    val searchStringAND = searchStringAndCreator(keyword)
                    val searchStringANDResult = titleRepository.search(searchStringAND)

                    val moodLong = mood?.toLong()
                    val genreLong = genre?.toLong()
                    val instrumentLong = instrument?.toLong()
                    val resultAND = titleRepository.tagSearchAND(tempo, moodLong, genreLong, instrumentLong)

                    val resultKeywordANDTag = searchStringANDResult.intersect(resultAND)
                    if (resultKeywordANDTag.isNullOrEmpty()) {
                        val searchStringOR = searchStringOrCreator(keyword)
                        val searchStringORResult = titleRepository.search(searchStringOR)
                        val resultORKeywordANDTag = searchStringORResult.intersect(resultAND)
                        if (resultORKeywordANDTag.isNullOrEmpty()) {
                            val resultTagOr = titleRepository.tagSearchOR(tempo, moodLong, genreLong, instrumentLong)
                            val resultOrKeywordANDORTag = resultORKeywordANDTag.intersect(resultTagOr)
                            if (resultOrKeywordANDORTag.isNullOrEmpty()) {
                                return resultORKeywordANDTag + resultTagOr
                            } else {
                                return resultOrKeywordANDORTag
                            }
                        } else {
                            return resultORKeywordANDTag
                        }
                    } else {
                        return resultKeywordANDTag
                    }
                }
            }
        }
    }

    fun searchStringAndCreator(keyword: String): String {
        val keywordReplaced = keyword.replace(Regex("\\s{2,}"), " ")
        val keywordTrimmed = keywordReplaced.trimEnd()
        val keywordSeperated = keywordTrimmed.split(" ").toTypedArray()
        return keywordSeperated.joinToString(" ") { "+$it" }
    }

    fun searchStringOrCreator(keyword: String): String {
        val keywordReplaced = keyword.replace(Regex("\\s{2,}"), " ")
        val keywordTrimmed = keywordReplaced.trimEnd()
        val keywordSeperated = keywordTrimmed.split(" ").toTypedArray()
        return keywordSeperated.joinToString(" ")
    }

    /*--------------------------------------------User----------------------------------------*/
    fun getAllUsers(): MutableIterable<Users> = usersRepository.findAll()


}
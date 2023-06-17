import com.htwk.musikdatenbank.entities.artist.ArtistConverter
import com.htwk.musikdatenbank.services.music.MusicService
import org.mapstruct.factory.Mappers
import org.openapitools.api.ArtistApi
import org.openapitools.model.ArtistView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArtistController (
    val musicService: MusicService,
    val converter: ArtistConverter = Mappers.getMapper(ArtistConverter::class.java)
): ArtistApi {
    override fun getArtists(): ResponseEntity<List<ArtistView>> {
        val artists = musicService.getAllArtists().map { converter.convertToView(it) }.toList()
        return ResponseEntity.ok(artists)
    }
}

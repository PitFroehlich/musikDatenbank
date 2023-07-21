package com.htwk.musikdatenbank.entities.title

import com.htwk.musikdatenbank.services.MusicService
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.mapstruct.factory.Mappers
import org.openapitools.api.TitleApi
import org.openapitools.api.TitleUploadApi
import org.openapitools.model.TitleUploadDto
import org.openapitools.model.TitleView
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class TitleController(
        val musicService: MusicService,
) : TitleApi, TitleUploadApi {
    val converter: TitleConverter = Mappers.getMapper(TitleConverter::class.java)

    override fun getTitles(): ResponseEntity<List<TitleView>> {
        val titles = musicService.getAllTitles().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(titles)
    }

    override fun searchTitles(
            keyword: String?,
            tempo: Int?,
            mood: Int?,
            genre: Int?,
            instrument: Int?
    ): ResponseEntity<List<TitleView>> {
        val titles =
                musicService.searchTitle(keyword, tempo, mood, genre, instrument).map { converter.convertToView(it) }
                        .toList()

        return ResponseEntity.ok(titles)
    }

    @PreAuthorize("hasRole('LABEL')")
    override fun uploadTitle(titleUploadDto: TitleUploadDto?): ResponseEntity<Unit> {
        if(titleUploadDto == null) {
            return ResponseEntity.badRequest().build()
        }
        musicService.uploadTitle(titleUploadDto)

        return ResponseEntity.status(201).build()
    }





    @GetMapping("/hallo")
    fun hallo(): ResponseEntity<Unit> {
        return ResponseEntity.status(200).build()
    }
}

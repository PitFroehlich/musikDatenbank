package com.htwk.musikdatenbank.entities.presskit

import com.htwk.musikdatenbank.services.MusicService
import jakarta.validation.Valid
import org.mapstruct.factory.Mappers
import org.openapitools.api.PresskitApi
import org.openapitools.model.PresskitView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.URLConnection


@RestController
class PresskitController(
    val musicService: MusicService,
    val converter: PresskitConverter = Mappers.getMapper(PresskitConverter::class.java)
): PresskitApi {
    override fun getPresskits(): ResponseEntity<List<PresskitView>> {
        val presskits = musicService.getAllPresskits().map { converter.toView(it) }.toList()
        return ResponseEntity.ok(presskits)
    }
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/presskit"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun test(@Valid @RequestPart("image") image: ByteArray?,@Valid @RequestPart("pdf") pdf: ByteArray?): ResponseEntity<PresskitView> {
        return ResponseEntity.ok(musicService.postPresskit(image, pdf))
    }

}
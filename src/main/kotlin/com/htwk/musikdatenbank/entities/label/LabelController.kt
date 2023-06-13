package com.htwk.musikdatenbank.entities.label

import com.htwk.musikdatenbank.services.user.UserService
import org.mapstruct.factory.Mappers
import org.openapitools.api.LabelApi
import org.openapitools.model.LabelView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LabelController(
    val userService: UserService,
    val converter: LabelConverter = Mappers.getMapper(
        LabelConverter::class.java
    )
) : LabelApi {

    override fun getLabels(): ResponseEntity<List<LabelView>> {
        val labels = userService.getAllLabels().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(labels)
    }
}
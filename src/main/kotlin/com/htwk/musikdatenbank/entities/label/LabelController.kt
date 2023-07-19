package com.htwk.musikdatenbank.entities.label

import com.htwk.musikdatenbank.services.UserService
import org.mapstruct.factory.Mappers
import org.openapitools.api.LabelApi
import org.openapitools.model.LabelView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LabelController(
    val userService: UserService,
) : LabelApi {
    val converter: LabelConverter = Mappers.getMapper(
        LabelConverter::class.java
    )

    override fun getLabels(): ResponseEntity<List<LabelView>> {
        val labels = userService.getAllLabels().map { converter.convertToView(it) }.toList()

        return ResponseEntity.ok(labels)
    }

    override fun createLabel(labelView: LabelView?): ResponseEntity<Unit> {
        if(labelView == null) {
            return ResponseEntity.badRequest().build()
        }

        labelView.let { converter.convertToEntity(it) }.let { userService.createLabel(it) }
        return ResponseEntity.status(201).build()
    }
}
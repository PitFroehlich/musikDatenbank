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

    override fun getLabel(labelId: Int): ResponseEntity<LabelView> {
        val label = converter.convertToView(userService.getLabel(labelId))
        println(arrayListOf(label))
        return ResponseEntity.ok(label)

    }

    override fun createLabel(labelView: LabelView?): ResponseEntity<LabelView> {
        if (labelView == null) {
            return ResponseEntity.badRequest().build()
        }

        val label = labelView.let { converter.convertToEntity(it) }.let { userService.createLabel(it) }
        return ResponseEntity.ok(converter.convertToView(label))
    }
}
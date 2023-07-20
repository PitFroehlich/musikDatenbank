package com.htwk.musikdatenbank.entities.presskit

import org.mapstruct.Mapper
import org.openapitools.model.PresskitView

@Mapper
interface PresskitConverter {
    fun toEntity(image: ByteArray, pdf: ByteArray): Presskit

    fun toView(presskit: Presskit): PresskitView
}
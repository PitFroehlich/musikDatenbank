package com.htwk.musikdatenbank.entities.links.audio_title_link

import org.springframework.data.repository.CrudRepository

interface AudioTitleLinkRepository: CrudRepository<AudioTitleLink, Long> {
    fun findByTitleId(titleId: Long): AudioTitleLink
}
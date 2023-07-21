package com.htwk.musikdatenbank.entities.links.audio_title_link

import jakarta.persistence.*


@Entity
class AudioTitleLink (@Id
                      @GeneratedValue(strategy = GenerationType.IDENTITY)
                      @Column(name = "id", updatable = false, nullable = false)
                      val id: Long?,
                      val titleId: Long,
                      val audioId: Long)
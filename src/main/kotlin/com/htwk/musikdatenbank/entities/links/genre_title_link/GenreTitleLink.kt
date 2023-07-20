package com.htwk.musikdatenbank.entities.links.genre_title_link

import jakarta.persistence.*

@Entity
class GenreTitleLink(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     @Column(name = "id", updatable = false, nullable = false)
                     val id: Long?,
                     val titleId: Long,
                     val genreId: Long)
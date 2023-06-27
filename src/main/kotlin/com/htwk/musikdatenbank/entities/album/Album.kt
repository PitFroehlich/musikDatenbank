package com.htwk.musikdatenbank.entities.album

import jakarta.persistence.*
import java.util.Date

@Entity
class Album (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var text: String,
    var cover: ByteArray,
    @Column(name = "release_year")
    var releaseDate: Date,
)
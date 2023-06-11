package com.htwk.musikdatenbank.entities.publicplaylist

import jakarta.persistence.*
import java.sql.Blob

@Entity
class PublicPlaylist (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var test: String,
    @Lob
    var previewPicture: Blob,
    var visible: Boolean
)
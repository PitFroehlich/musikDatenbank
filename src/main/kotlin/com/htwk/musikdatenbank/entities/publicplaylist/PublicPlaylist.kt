package com.htwk.musikdatenbank.entities.publicplaylist

import jakarta.persistence.*
import org.springframework.core.io.Resource
import java.sql.Blob

@Entity
class PublicPlaylist (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var text: String,
    var previewPicture: ByteArray,
    var visible: Boolean
)
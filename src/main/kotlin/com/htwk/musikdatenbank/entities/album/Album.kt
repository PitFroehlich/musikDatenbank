package com.htwk.musikdatenbank.entities.album

import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.presskit.Presskit
import jakarta.persistence.*
import java.util.*

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
    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    var labelId: Label,
    @ManyToOne
    @JoinColumn(name = "presskit_id", referencedColumnName = "id")
    var presskitId: Presskit

    )
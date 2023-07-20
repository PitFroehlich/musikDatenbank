package com.htwk.musikdatenbank.entities.publicplaylist

import com.htwk.musikdatenbank.entities.label.Label
import jakarta.persistence.*

@Entity
class PublicPlaylist (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var text: String,
    var previewPicture: ByteArray,
    var visible: Boolean,
    @ManyToOne
    @JoinColumn(name = "label_id")
    var label: Label,
)
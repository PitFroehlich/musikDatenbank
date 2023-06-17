package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.entities.label.Label
import jakarta.persistence.*
import java.sql.Blob

@Entity
class Audio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "label_id")
    var label: Label,
    var wav: ByteArray,
    var mp3: ByteArray,

)
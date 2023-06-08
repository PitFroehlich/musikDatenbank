package com.htwk.musikdatenbank.audio

import com.htwk.musikdatenbank.label.Label
import jakarta.persistence.*
import java.sql.Blob

@Entity
class Audio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    @ManyToOne
    @JoinColumn(name="label_id")
    var label: Label,
    @Lob
    var wav: Blob,
    @Lob
    var mp3: Blob,
    )
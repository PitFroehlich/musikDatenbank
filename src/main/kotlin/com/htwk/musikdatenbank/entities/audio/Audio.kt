package com.htwk.musikdatenbank.entities.audio

import com.htwk.musikdatenbank.entities.label.Label
import jakarta.persistence.*

@Entity
class Audio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    var label: Label,
    var wav: ByteArray,

)
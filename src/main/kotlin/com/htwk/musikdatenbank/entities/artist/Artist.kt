package com.htwk.musikdatenbank.entities.artist

import jakarta.persistence.*

@Entity
class Artist (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long?,
    var name: String,
    var image: ByteArray,
    var biography: String
    )
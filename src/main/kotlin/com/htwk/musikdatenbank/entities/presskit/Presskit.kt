package com.htwk.musikdatenbank.entities.presskit

import jakarta.persistence.*

@Entity
class Presskit (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var image: ByteArray,
    var pdf: ByteArray,
)
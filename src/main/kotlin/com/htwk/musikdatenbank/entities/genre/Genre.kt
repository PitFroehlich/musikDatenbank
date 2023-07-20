package com.htwk.musikdatenbank.entities.genre

import jakarta.persistence.*

@Entity
class Genre (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
)
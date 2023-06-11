package com.htwk.musikdatenbank.entities.instrument

import jakarta.persistence.*


@Entity
class Instrument(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String
)
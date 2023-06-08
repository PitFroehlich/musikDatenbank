package com.htwk.musikdatenbank.mood

import jakarta.persistence.*


@Entity
class Mood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "name")
    var name: String = ""

)

package com.htwk.musikdatenbank.entities.user

import jakarta.persistence.*

@Entity
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var email: String,
    @Column(name = "password_hash")
    var passwordHash: String,
)
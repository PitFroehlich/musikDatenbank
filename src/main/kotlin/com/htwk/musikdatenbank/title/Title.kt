package com.htwk.musikdatenbank.title

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Title {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long? = null

}
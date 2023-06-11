package com.htwk.musikdatenbank.entities.label

import com.htwk.musikdatenbank.entities.audio.Audio
import jakarta.persistence.*

@Entity
class Label(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var passwordHash: String,
    var email: String,
    var biography: String
){
    @OneToMany(mappedBy = "label")
    private val audios: List<Audio>? = null
}
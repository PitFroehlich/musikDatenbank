package com.htwk.musikdatenbank.label

import com.htwk.musikdatenbank.audio.Audio
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
class Label(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    var name: String,
    var password: String,
    @Email
    var email: String,
    var biography: String
){
    @OneToMany(mappedBy = "label")
    private val audios: List<Audio>? = null
}
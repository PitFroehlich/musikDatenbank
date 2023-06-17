package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.entities.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class PrivatePlaylist (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userId: User,
    var name: String,
    var text: String,
    @Column(name = "preview_picture")
    var previewPicture: ByteArray,
    )
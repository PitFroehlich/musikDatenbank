package com.htwk.musikdatenbank.entities.user

import com.htwk.musikdatenbank.entities.privateplaylist.PrivatePlaylist
import jakarta.persistence.*

@Entity
class Users (
    @Id
    @Column(name = "username", updatable = false, nullable = false)
    var username: String,
    var email: String,
    var password: String,
    var enabled: Boolean,
) {
    @OneToMany(mappedBy = "users")
    private val privatePlaylists: List<PrivatePlaylist>? = null
}
package com.htwk.musikdatenbank.entities.authorities

import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import java.io.Serializable

@Entity
@IdClass(Authorities::class)
class Authorities(
    @Id
    var username: String,
    @Id
    var authority: String
) : Serializable {
    companion object {
        @Embeddable
        class AuthoritiesPk(username: String, authority: String) : Serializable

    }

}
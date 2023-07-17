package com.htwk.musikdatenbank.entities.authorities

import org.springframework.data.repository.CrudRepository

interface AuthoritiesRepository: CrudRepository<Authorities, Authorities.Companion.AuthoritiesPk> {
    fun findAllByUsername(username: String): List<Authorities>
}
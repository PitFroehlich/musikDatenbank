package com.htwk.musikdatenbank.entities.user

import org.springframework.data.repository.CrudRepository

interface UsersRepository: CrudRepository<Users, String> {
    fun findByUsername(username: String): Users?
}
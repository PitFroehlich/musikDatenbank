package com.htwk.musikdatenbank.entities.user

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UsersRepository: CrudRepository<Users, String> {
    fun findByUsername(username: String): Optional<Users>
}
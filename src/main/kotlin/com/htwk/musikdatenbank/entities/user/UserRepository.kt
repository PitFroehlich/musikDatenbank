package com.htwk.musikdatenbank.entities.user

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long>
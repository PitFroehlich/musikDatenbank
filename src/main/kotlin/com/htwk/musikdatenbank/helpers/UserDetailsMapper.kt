package com.htwk.musikdatenbank.helpers

import com.htwk.musikdatenbank.entities.user.Users
import org.springframework.security.core.userdetails.UserDetails

//@Mapper
interface UserDetailsMapper {
    fun convertToUserDetails(users: Users): UserDetails
}
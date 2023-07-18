package com.htwk.musikdatenbank.services

import com.htwk.musikdatenbank.entities.authorities.Authorities
import com.htwk.musikdatenbank.entities.authorities.AuthoritiesRepository
import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.label.LabelRepository
import com.htwk.musikdatenbank.entities.user.Users
import com.htwk.musikdatenbank.entities.user.UsersRepository
import org.openapitools.model.AuthenticationView
import org.openapitools.model.UserView
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    val labelRepository: LabelRepository,
    val usersRepository: UsersRepository,
    val authoritiesRepository: AuthoritiesRepository,
    val passwordEncoder: PasswordEncoder
)
//    : UserDetailsService
{

    /*------------------------------------Label------------------------------------------------*/
    fun getAllLabels(): MutableIterable<Label> = labelRepository.findAll()


    /*------------------------------------User------------------------------------------------*/
    fun createUser(userView: UserView) {

        val users = usersRepository.save(
            Users(
                username = userView.username!!,
                email = userView.email!!,
                password = passwordEncoder.encode(userView.password),
                enabled = true
            )
        )

        authoritiesRepository.save(Authorities(username = userView.username!!, authority = "ROLE_" + userView.role!!.value))
    }

    fun loginUser(authenticationView: AuthenticationView): Boolean {
        val user = usersRepository.findByUsername(authenticationView.username!!)

        return passwordEncoder.encode(authenticationView.password!!).equals(user.password)
    }


    fun getUser(username: String): UserView {
        val authorities = authoritiesRepository.findAllByUsername(username)

        val user = usersRepository.findByUsername(username)

        return UserView(
            username = username,
            password = user.password,
            email = user.email,
            role = UserView.Role.valueOf(authorities.firstOrNull()!!.authority.removePrefix("ROLE_"))
        )
    }
}
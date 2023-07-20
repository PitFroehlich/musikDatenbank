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
import java.lang.IllegalStateException
import java.util.*


@Service
class UserService(
        val labelRepository: LabelRepository,
        val usersRepository: UsersRepository,
        val authoritiesRepository: AuthoritiesRepository,
        val passwordEncoder: PasswordEncoder
) {
    /*------------------------------------Label------------------------------------------------*/
    fun getAllLabels(): MutableIterable<Label> = labelRepository.findAll()

    fun createLabel(label: Label) {
        val labelExists = labelRepository.existsById(label.id)
        if (labelExists) {
            throw IllegalStateException("Label mit Id ${label.id} existiert bereits")
        }
        labelRepository.save(label)
    }

    /*------------------------------------User------------------------------------------------*/
    fun createUser(userView: UserView) {
        if (userView.username != null && userView.email != null) {


            val user = usersRepository.findByUsername(userView.username!!)

            if (!user.isEmpty) {
                throw IllegalStateException("User with name ${user.get().username} already exists")
            }

            usersRepository.save(
                    Users(
                            username = userView.username!!,
                            email = userView.email!!,
                            password = passwordEncoder.encode(userView.password),
                            enabled = true
                    )
            )

            authoritiesRepository.save(
                    Authorities(
                            username = userView.username!!,
                            authority = "ROLE_" + userView.role!!.value
                    )
            )
        }
    }

    fun loginUser(authenticationView: AuthenticationView): Boolean {
        val user = usersRepository.findByUsername(authenticationView.username!!)
        if (user.isEmpty) {
            throw IllegalStateException("User with name ${user.get().username} does not exist")
        }

        return passwordEncoder.encode(authenticationView.password!!).equals(user.get().password)
    }

    fun getUser(username: String): UserView {
        val authorities = authoritiesRepository.findAllByUsername(username)

        val user = usersRepository.findByUsername(username)

        if (user.isEmpty) {
            throw IllegalStateException("User with name ${user.get().username} does not exist")
        }
        return UserView(
                username = username,
                password = user.get().password,
                email = user.get().email,
                role = UserView.Role.valueOf(authorities.firstOrNull()!!.authority.removePrefix("ROLE_")
                        .replaceFirstChar { it.lowercase(Locale.getDefault()) })
        )
    }


}
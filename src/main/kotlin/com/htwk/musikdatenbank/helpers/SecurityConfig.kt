package com.htwk.musikdatenbank.helpers

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Description
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig() {
    @Bean
    @Primary
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    fun users(dataSource: DataSource, passwordEncoder: PasswordEncoder): UserDetailsService? {
        // The builder will ensure the passwords are encoded before saving in memory
        return JdbcUserDetailsManager(dataSource)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }


    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf {
            it.disable()
        }
            .authorizeHttpRequests { request ->
                request.requestMatchers("/user", "/login").permitAll().anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())

        return httpSecurity.build()
    }
}

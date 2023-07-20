package com.htwk.musikdatenbank.helpers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig() {
    @Bean
    @Primary
    fun users(dataSource: DataSource, passwordEncoder: PasswordEncoder): UserDetailsService? {
        return JdbcUserDetailsManager(dataSource)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    private val corsOriginPatterns: String = "http://localhost:4200, http://localhost:8080"

    @Bean
    fun addCorsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                val allowedOrigins = corsOriginPatterns.split(",").toTypedArray()
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOriginPatterns(*allowedOrigins)
                    .allowCredentials(true)
                    .allowedHeaders("authorization", "content-type")
            }
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200", "http://localhost:8080")
        configuration.allowedMethods = listOf(
            "GET", "POST", "PUT", "PATCH",
            "DELETE", "OPTIONS"
        )
        configuration.allowedOriginPatterns = listOf("http://localhost:4200", "http://localhost:8080")
        configuration.allowCredentials = true
        configuration.allowedHeaders = listOf(
            "authorization", "content-type",
        )
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .cors {  }
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests { request ->
                request.requestMatchers("/user", "/login", "/label").permitAll().anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())




        return httpSecurity.build()
    }
}

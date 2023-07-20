package com.htwk.musikdatenbank.entities.mood

import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface MoodRepository: CrudRepository<Mood, Long> {
    fun findByName(name: String): Optional<Mood>
}
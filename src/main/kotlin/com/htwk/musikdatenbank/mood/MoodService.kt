package com.htwk.musikdatenbank.mood

import org.springframework.stereotype.Service

@Service
class MoodService(val moodRepository: MoodRepository) {
    fun getMood() = moodRepository.findAll()
}
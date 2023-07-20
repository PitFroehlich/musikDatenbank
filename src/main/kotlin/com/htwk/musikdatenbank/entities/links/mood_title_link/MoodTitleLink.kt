package com.htwk.musikdatenbank.entities.links.mood_title_link

import jakarta.persistence.*

@Entity
class MoodTitleLink(@Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    @Column(name = "id", updatable = false, nullable = false)
                    val id: Long?,
                    val titleId: Long,
                    val moodId: Long
)
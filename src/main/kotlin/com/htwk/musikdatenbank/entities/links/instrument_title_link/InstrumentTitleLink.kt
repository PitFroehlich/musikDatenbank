package com.htwk.musikdatenbank.entities.links.instrument_title_link

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class InstrumentTitleLink(@Id
                          @GeneratedValue(strategy = GenerationType.IDENTITY)
                          @Column(name = "id", updatable = false, nullable = false)
                          val id: Long?,
                          val titleId: Long,
                          val instrumentId: Long) {
}
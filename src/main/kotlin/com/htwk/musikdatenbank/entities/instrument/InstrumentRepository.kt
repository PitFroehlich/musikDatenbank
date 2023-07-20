package com.htwk.musikdatenbank.entities.instrument

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface InstrumentRepository: CrudRepository<Instrument, Long> {
    fun findByName(name: String): Optional<Instrument>
}
package com.htwk.musikdatenbank.instrument

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstrumentRepository: CrudRepository<Instrument, Long>
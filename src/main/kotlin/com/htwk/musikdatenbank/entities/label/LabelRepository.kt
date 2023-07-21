package com.htwk.musikdatenbank.entities.label

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

interface LabelRepository: CrudRepository<Label, Long> {
    fun findByName(name: String): Optional<Label>
}
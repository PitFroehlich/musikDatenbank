package com.htwk.musikdatenbank.entities.label

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository: CrudRepository<Label, Long>
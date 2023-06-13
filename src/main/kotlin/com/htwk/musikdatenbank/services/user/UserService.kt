package com.htwk.musikdatenbank.services.user

import com.htwk.musikdatenbank.entities.label.Label
import com.htwk.musikdatenbank.entities.label.LabelRepository
import org.springframework.stereotype.Service


@Service
class UserService(val labelRepository: LabelRepository) {
    /*------------------------------------Label------------------------------------------------*/
    fun getAllLabels(): MutableIterable<Label> = labelRepository.findAll()
}
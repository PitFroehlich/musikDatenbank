package com.htwk.musikdatenbank.services.user

import com.htwk.musikdatenbank.label.Label
import com.htwk.musikdatenbank.label.LabelRepository
import org.springframework.stereotype.Service


@Service
class UserService(val labelRepository: LabelRepository) {
    /*------------------------------------Label------------------------------------------------*/
    fun getAllLabels(): MutableIterable<Label> = labelRepository.findAll()
}
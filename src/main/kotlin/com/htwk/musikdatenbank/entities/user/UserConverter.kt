package com.htwk.musikdatenbank.entities.user

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.UserView

@Mapper
interface UserConverter: Converter<UserView, User>
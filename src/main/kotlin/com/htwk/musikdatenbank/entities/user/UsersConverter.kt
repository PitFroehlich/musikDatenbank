package com.htwk.musikdatenbank.entities.user

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.openapitools.model.UserView

@Mapper
interface UsersConverter: Converter<UserView, Users> {

    @Mapping(target="enabled", ignore = true)
    override fun convertToEntity(view: UserView): Users

    @Mapping(target="role", ignore = true)
    @Mapping(target="labelId", ignore = true)
    override fun convertToView(entity: Users): UserView
}
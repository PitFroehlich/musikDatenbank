package com.htwk.musikdatenbank.entities.owner

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.OwnerView

@Mapper
interface OwnerConverter: Converter<OwnerView, Owner>
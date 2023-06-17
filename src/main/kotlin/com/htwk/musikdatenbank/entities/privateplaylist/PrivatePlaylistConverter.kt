package com.htwk.musikdatenbank.entities.privateplaylist

import com.htwk.musikdatenbank.helpers.Converter
import org.mapstruct.Mapper
import org.openapitools.model.PrivatePlaylistView

@Mapper
interface PrivatePlaylistConverter: Converter<PrivatePlaylistView, PrivatePlaylist>

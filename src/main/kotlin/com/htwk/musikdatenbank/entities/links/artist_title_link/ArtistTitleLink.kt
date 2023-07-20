package com.htwk.musikdatenbank.entities.links.artist_title_link


import jakarta.persistence.*

@Entity
class ArtistTitleLink(@Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    @Column(name = "id", updatable = false, nullable = false)
                    val id: Long?,
                    val titleId: Long,
                    val artistId: Long
)
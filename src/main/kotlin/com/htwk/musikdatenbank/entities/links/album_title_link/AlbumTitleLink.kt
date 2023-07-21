package com.htwk.musikdatenbank.entities.links.album_title_link

import com.htwk.musikdatenbank.entities.album.Album
import com.htwk.musikdatenbank.entities.title.Title
import jakarta.persistence.*

@Entity
class AlbumTitleLink (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    var titleId: Title,
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    var albumId: Album
)
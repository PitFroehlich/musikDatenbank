package com.htwk.musikdatenbank.entities.links.album_title_link

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AlbumTitleLinkRepository: CrudRepository<AlbumTitleLink, Long> {
    @Modifying
    @Transactional
    @Query("""INSERT INTO album_title_link (title_id, album_id)
VALUES (?1, ?2);""", nativeQuery = true)
    fun addTitleToAlbum(titleId: Long, albumId: Long)

    @Modifying
    @Transactional
    @Query("DELETE FROM album_title_link WHERE album_title_link.id  = ?1 ", nativeQuery = true)
    fun deleteTitleFromAlbum(albumId: Int)
}

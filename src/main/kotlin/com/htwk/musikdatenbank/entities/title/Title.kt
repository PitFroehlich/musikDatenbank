package com.htwk.musikdatenbank.entities.title
import com.htwk.musikdatenbank.entities.label.Label
import jakarta.persistence.*
import java.util.Date

@Entity
class Title(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long?,
    @ManyToOne
    @JoinColumn(name = "label_id")
    var label: Label,
    var name: String,
    @Column(name = "gema_nr")
    var gemaNr: String,
    var bpm: Int,
    @Column(name = "release_date")
    var releaseDate: Date,
    var visible: Boolean,
    var cover: ByteArray
)
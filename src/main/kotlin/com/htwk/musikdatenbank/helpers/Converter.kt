package com.htwk.musikdatenbank.helpers


interface Converter<V, E> {
    fun convertToEntity(view: V): E

    fun convertToView(entity: E): V
}

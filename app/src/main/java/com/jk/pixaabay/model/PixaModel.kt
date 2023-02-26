package com.jk.pixaabay.model

data class PixaModel(
    var hits: ArrayList<Hits>
)

data class Hits(
    var largeImageURL: String
)

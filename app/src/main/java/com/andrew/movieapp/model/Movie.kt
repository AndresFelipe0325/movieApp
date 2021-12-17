package com.andrew.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("overview")
    val synopsis: String? = "",
    @SerializedName("poster_path")
    val imgUrl: String? = ""


) {
    override fun toString(): String = "${this.id}/${this.title}/${this.synopsis}/${this.imgUrl}"
}

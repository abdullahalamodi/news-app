package dev.alamodi.newsapi.data.models

data class News(
    val id: Int,
    val title: String,
    val date: String,
    val description: String,
    val image: String,
    val categoryId: Int
)
package study.domain

import java.time.LocalDateTime

data class RssItem(
    val title: String,
    val link: String,
    val publishedAt: LocalDateTime,
)

package study.view

import study.domain.RssItem

object OutputView {
    fun printResult(list: List<RssItem>) {
        for ((index, item) in list.withIndex()) {
            println("[${index + 1}] ${item.title} (${item.publishedAt}) - ${item.link}  ")
        }
    }

    fun printNewFeeds(list: List<RssItem>) {
        for ((index, item) in list.withIndex()) {
            println("[new] ${item.title} (${item.publishedAt}) - ${item.link}  ")
        }
    }
}

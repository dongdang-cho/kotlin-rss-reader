package study.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import study.domain.FeedUrls
import study.domain.RssItem
import study.domain.RssReader
import study.view.InputView
import study.view.OutputView
import kotlin.time.Duration.Companion.seconds

class RssController {
    private val urls = FeedUrls.allFeeds

    fun run() =
        runBlocking {
            val feedList = mutableListOf<RssItem>()

            val inputJob =
                launch(Dispatchers.IO) {
                    while (isActive) {
                        val keyword = InputView.getSearchKeword()
                        if (feedList.isEmpty()) {
                            println("수집된 피드가 없습니다.")
                        }
                        if (keyword === "") {
                            OutputView.printResult(feedList)
                        } else {
                            val filterList = feedList.filter { rssItem -> rssItem.title.contains(keyword) }

                            if (filterList.isEmpty()) {
                                println("관련 피드가 없습니다.")
                            } else {
                                OutputView.printResult(filterList)
                            }
                        }
                    }
                }
            val feedJob =
                launch(Dispatchers.IO) {
                    while (isActive) {
                        val oldFeedList = mutableListOf<RssItem>()
                        oldFeedList.addAll(feedList)
                        feedList.clear()

                        for (url in urls) {
                            val item = RssReader().read(url)
                            feedList.addAll(item)
                        }
                        if (oldFeedList.isNotEmpty()) {
                            val newFeeds = feedList.diffWith(oldFeedList)
                            OutputView.printNewFeeds(newFeeds)
                        }

                        delay(10.seconds)
                    }
                }

            inputJob.join()
            feedJob.cancel()
        }

    fun List<RssItem>.diffWith(previous: List<RssItem>): List<RssItem> {
        if (previous.isEmpty()) return this

        // removeAll(Predicate) 활용 → 가독성 & 의도 명확
        return toMutableList().apply {
            removeAll(previous)
        }
    }
}

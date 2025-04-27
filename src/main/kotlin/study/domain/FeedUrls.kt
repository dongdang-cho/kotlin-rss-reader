package study.domain

object FeedUrls {
    const val YUN_BLOG = "https://cheese10yun.github.io/rss2.xml"
    const val JOJOLDU_BLOG = "https://jojoldu.tistory.com/rss"
    const val WOOWAHAN_TECH = "https://techblog.woowahan.com/feed/"
    const val NHN_CLOUD_MEETUP = "https://meetup.toast.com/rss"

    val allFeeds =
        listOf(
            YUN_BLOG,
            JOJOLDU_BLOG,
            WOOWAHAN_TECH,
            NHN_CLOUD_MEETUP,
        )
}

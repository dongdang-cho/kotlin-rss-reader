package study.domain

import org.w3c.dom.Element
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory

class RssReader {
    fun read(url: String): List<RssItem> {
        val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val document = builder.parse(URL(url).openStream())
        val channel = document.getElementsByTagName("channel").item(0)

        // channel 하위 item 요소들 파싱
        val items =
            List(channel.childNodes.length) { channel.childNodes.item(it) }
                .filterIsInstance<Element>()
                .filter { it.tagName == "item" }

        val rssList =
            items.map { elem ->
                val title = elem.textOf("title")
                val link = elem.textOf("link")
                val pubDate =
                    LocalDateTime.parse(
                        elem.textOf("pubDate"),
                        DateTimeFormatter.RFC_1123_DATE_TIME,
                    )
                RssItem(title, link, pubDate)
            }
        return rssList
    }

    private fun Element.textOf(tagName: String): String {
        return getElementsByTagName(tagName).item(0)?.textContent.orEmpty()
    }
}

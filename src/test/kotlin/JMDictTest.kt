import helpers.StringLoader
import jmdict.Dictionary
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import resources.JMDictTestResource
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.containsExactlyInAnyOrder
import strikt.assertions.isEqualTo

class JMDictTest {
    lateinit var results: Dictionary

    @BeforeEach
    fun setup() {
        results = Dictionary(StringLoader(JMDictTestResource.string))
    }

    @Test
    fun `loads and parses dictionary (no results check)`() {
        println(results)
        // blank
    }

    @Test
    fun `contains all expected results`() {
        expectThat(results.entries())
            .containsExactlyInAnyOrder(listOf("一番", "一番上", "赤", "毎日", "日日"))
    }

    @Test
    fun `Retains different entries with the same title`() {
        expectThat(results.entry("一番")!!.size)
            .isEqualTo(2)
    }

    @Test
    fun `contains expected kanji`() {
        expectThat(results.entry("赤")!!.first().kanjiElement!!.flatMap { it.element })
            .containsExactly(listOf("赤", "紅", "朱", "緋"))
    }
}

package az.siftoshka.junkyconverter.domain.usecase

import az.siftoshka.junkyconverter.data.repository.TestJunkRepository
import az.siftoshka.junkyconverter.domain.util.moneyFormat
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Testing of getting junks.
 */
class GetJunksTest {

    private lateinit var getJunks: GetJunks
    private lateinit var testRepository: TestJunkRepository

    @Before
    fun setUpTesting() {
        testRepository = TestJunkRepository()
        getJunks = GetJunks(testRepository)

        val initJunks = mutableListOf<Junk>()
        ('a'..'z').forEachIndexed { index, char ->
            initJunks.add(
                Junk(
                    id = index,
                    name = char.toString(),
                    value = index.toFloat(),
                    icon = index,
                    iconDescription = index
                )
            )
        }
        initJunks.shuffle()
        runBlocking { initJunks.forEach { testRepository.insertJunk(it) } }
    }

    @Test
    fun `Order of junks by name -) Correct order from a to z`() = runBlocking {
        val junks = getJunks().first()

        for (i in 0..junks.size - 2) {
            assertThat(junks[i].name).isLessThan(junks[i + 1].name)
        }
    }

    @Test
    fun `Testing of Money Formatter -) Correct result`() {
        "10000".apply { assertThat(moneyFormat()).isEqualTo("10,000") }
        "10000.0".apply { assertThat(moneyFormat()).isEqualTo("10,000.00") }
        "10000.5".apply { assertThat(moneyFormat()).isEqualTo("10,000.50") }
        "123456.7".apply { assertThat(moneyFormat()).isEqualTo("123,456.70") }
        "123456.73".apply { assertThat(moneyFormat()).isEqualTo("123,456.73") }
        "2.00".apply { assertThat(moneyFormat()).isEqualTo("2.00") }
        "1000000.5".apply { assertThat(moneyFormat()).isEqualTo("1,000,000.50") }
        "1200330.56".apply { assertThat(moneyFormat()).isEqualTo("1,200,330.56") }
    }
}
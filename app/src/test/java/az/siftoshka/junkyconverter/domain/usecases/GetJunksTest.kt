package az.siftoshka.junkyconverter.domain.usecases

import az.siftoshka.junkyconverter.data.repository.TestJunkRepository
import org.junit.Before

/**
 * Testing of getting junks
 */
class GetJunksTest {

    private lateinit var getJunks: GetJunks
    private lateinit var testRepository: TestJunkRepository

    @Before
    fun setUpTesting() {
        testRepository = TestJunkRepository()
        getJunks = GetJunks(testRepository)
    }

}
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CalculatorTest {

//    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(StandardTestDispatcher())

    private val calculator = MyCalculator()

    /**
     * AAA Pattern.
     * Arrange, Act, Assert
     */
    @Test
    fun `add integers two plus two returns four`() {
        // Arrange
        val a = 3
        val b = 3
        val expected = 6

        // Act
        val actual = calculator.addIntegers(a, b)

        // Assert
        assertEquals(expected, actual, "Sum should be $expected")
    }

    @Test
    fun `add floats two point five plus three point five returns six`() {
        // Arrange
        val a = 2.5f
        val b = 3.5f
        val expected = 6.0f

        // Act
        val actual = calculator.addFloats(a, b)

        // Assert
        assertEquals(expected, actual, 0.0001f,"Sum should be $expected")
    }

    @Test
    fun `increment state once increments by one`() {
        // Arrange
        calculator.incrementState()
        val expected = 1

        // Act
        val actual = calculator.getState()

        // Assert
        assertEquals(expected, actual, "State should be incremented by $expected")
    }

    @Test
    fun `fetch result async returns expected result`() = runBlocking {
        // Arrange
        val expected = 56

        // Act
        val actual = calculator.fetchResultAsync()

        // Assert
        assertEquals(expected, actual, "Result should be $expected")
    }

    @Test
    fun `fetch result flow returns expected flow`() = runBlocking {
        // Arrange
        val expected = listOf(1, 2, 3)

        // Act
        val actual = calculator.fetchResultFlow().toList()

        // Assert
        assertContentEquals(expected, actual, "Flow should emit $expected") // Compare with 2 list
    }

    @Test
    fun `update state flow updates state flow value`() = testScope.runTest {
        // Arrange
        val newValue = 5
        val expected = 5

        // Act
        val differentResult = async { calculator.updateStateFlow(newValue) }
        advanceTimeBy(500L)
        differentResult.await()
        val actual = calculator.stateFlow.value

        // Assert
        assertEquals(expected, actual, "StateFlow value should be updated to $expected")
    }

    @Test
    fun `update state flow with advanceTimeBy updates state flow value`() = runTest {
        // Arrange
        val newValue = 5
        val expected = 5

        // Act
        advanceTimeBy(500L)
        calculator.updateStateFlow(newValue)
        val actual = calculator.stateFlow.value

        // Assert
        assertEquals(expected, actual, "StateFlow value should be updated to $expected")
    }

}
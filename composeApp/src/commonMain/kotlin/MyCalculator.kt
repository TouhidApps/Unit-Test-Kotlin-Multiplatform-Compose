import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class MyCalculator {

    private var internalState: Int = 0
    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val stateFlow: StateFlow<Int> = _stateFlow

    fun addIntegers(a: Int, b: Int): Int {
        return a + b
    }

    fun addFloats(a: Float, b: Float): Float {
        return a + b
    }

    fun incrementState(): Int {
        internalState += 1
        return internalState
    }

    fun getState(): Int {
        return internalState
    }

    suspend fun fetchResultAsync(): Int {
        delay(1000) // Simulate long running operation
        return 56
    }

    fun fetchResultFlow(): Flow<Int> = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    suspend fun updateStateFlow(newValue: Int) {
        delay(500L)
        _stateFlow.value = newValue
    }

}
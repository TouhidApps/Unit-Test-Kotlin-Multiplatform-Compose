import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

class CounterKtTest {

    @ExperimentalTestApi
    @Test
    fun testCountingUp() = runComposeUiTest {

        setContent {
            Counter()
        }

        onNodeWithText("0").assertExists() // Find the node and initial text sould display 0 value
        onNodeWithText("1").assertDoesNotExist() // Node text doesn't have 1 value
        onNodeWithText("Increment").performClick() // Find the button with text Increment & Click on it
        onNodeWithText("1").assertExists() // As we perform click on previous test the value should be 1
        onNodeWithText("0").assertDoesNotExist() // Text node value should not be 0 as we already incremented the value by button click

    }

}
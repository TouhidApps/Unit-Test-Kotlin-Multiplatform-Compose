import assertk.assertThat
import assertk.assertions.isEqualTo
import org.jetbrains.compose.resources.getString
import kotlin.test.Test

class StringUtilsKtTest {

    @Test
    fun testGetStringLength() {
        assertThat(getStringLength("Touhid")).isEqualTo(6)
    }

}
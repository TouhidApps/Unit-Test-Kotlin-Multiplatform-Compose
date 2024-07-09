import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test


class MyUtilsKtTest {

    @Test
    fun testGetStringLength() {
        assertThat(getStringLength("iOS")).isEqualTo(3)
    }

}
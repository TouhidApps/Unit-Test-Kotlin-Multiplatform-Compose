import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test
import kotlin.test.assertTrue

class MyUtilsTest {

    @Test
    fun testGetInitials() {
        val fname= "touhid apps"
        assertThat(getMyShortName(fname)).isEqualTo("TA")
    }

    @Test
    fun testMyCode() {
        assertTrue(true)
    }

}
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class MyUtilsTest {

    @Test
    fun testGetInitials() {
        val fname= "dkjf skdf"
        assertThat(getMyShortName(fname)).isEqualTo("DS")
    }


}
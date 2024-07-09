import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import kotlin.test.Test

class MyTest {

    @Test
    fun testPackageName() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertThat(context.packageName).isNotEqualTo("com.example")
    }

}
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SimpleTests {
    @Test
    fun testMath() {
        assertEquals(4, 2 * 2)
        assertNotEquals(5, 2 + 2)
    }
}

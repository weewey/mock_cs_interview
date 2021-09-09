import org.junit.Test
import kotlin.test.assertEquals

internal class IntegrationTest {
    @Test
    fun `it should return the expected output`() {
        val input = "1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85"
        val expected = "Taxes: 1.50 Total: 29.83"
        val actual = execute(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `when there is imported good, it should return the expected output`() {
        val input = "1 imported box of chocolates at 10.00 1 imported bottle of perfume at 47.50"
        val expected = "Taxes: 7.65 Total: 65.15"
        val actual = execute(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `when there is imported good v2, it should return the expected output`() {
        val input = "1 imported bottle of perfume at 27.99 1 bottle of perfume at 18.99 1 packet of headache pills at 9.75 1 imported box of chocolates at 11.25"
        val expected = "Taxes: 6.70 Total: 74.68"
        val actual = execute(input)
        assertEquals(expected, actual)
    }
}
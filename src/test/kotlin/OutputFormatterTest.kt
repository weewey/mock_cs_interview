import models.Item
import models.Receipt
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

internal class OutputFormatterTest {
    @Test
    fun `it should return the expected string`() {
        val receipt = Receipt(taxes = BigDecimal.TEN, total = BigDecimal("20"))
        val expected = "Taxes: 10 Total: 20"
        assertEquals(expected,OutputFormatter.formatOutputString(receipt))
    }
}
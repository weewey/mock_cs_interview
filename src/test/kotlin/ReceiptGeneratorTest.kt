import models.Item
import models.ItemCategory
import models.Receipt
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

internal class ReceiptGeneratorTest {
    @Test
    fun `it should return the expected receipt`() {
        val items = listOf<Item>(
            Item(
                ItemCategory.Books,
                BigDecimal("12.49"), false, 1
            ),
            Item(
                ItemCategory.Others,
                BigDecimal("14.99"), false, 1
            ),
            Item(
                ItemCategory.Food,
                BigDecimal("0.85"), false, 1
            )
        )
        val expected = Receipt(
            taxes = BigDecimal("1.50"),
            total = BigDecimal("29.83")
        )
        val actual = ReceiptGenerator.generate(items)
        assertEquals(expected, actual)
    }

    @Test
    fun `when there is multiple item, it should return the expected receipt`() {
        val items = listOf<Item>(
            Item(
                ItemCategory.Books,
                BigDecimal("12.49"), false, 2
            ),
            Item(
                ItemCategory.Others,
                BigDecimal("14.99"), false, 1
            ),
            Item(
                ItemCategory.Food,
                BigDecimal("0.85"), false, 1
            )
        )
        val expected = Receipt(
            taxes = BigDecimal("1.50"),
            total = BigDecimal("42.32")
        )
        val actual = ReceiptGenerator.generate(items)
        assertEquals(expected, actual)
    }
}
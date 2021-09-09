import models.Item
import models.ItemCategory
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

internal class InputParserTest {
    @Test
    fun `it should return the expected list of items`() {
        val input = "1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85"
        val expected = listOf(
            Item(category = ItemCategory.Books, price = BigDecimal("12.49"), imported = false, count = 1),
            Item(category = ItemCategory.Others, price = BigDecimal("14.99"), imported = false, count = 1),
            Item(category = ItemCategory.Food, price = BigDecimal("0.85"), imported = false, count = 1),
        )
        assertEquals(expected, InputParser.parse(input))
    }

    @Test
    fun `when the item is imported, it should build the expected item`() {
        val item = InputParser.buildItem(listOf("1 imported book at 12.49", "1", "imported book", "at", "12.49"))
        assertEquals(ItemCategory.Books, item.category)
        assertEquals(1, item.count)
        assertEquals(true, item.imported)
        assertEquals(BigDecimal("12.49"), item.price)
    }

    @Test
    fun `it should build the expected item`() {
        val item = InputParser.buildItem(listOf("1 book at 12.49", "1", "book", "at", "12.49"))
        assertEquals(
            Item(
                category = ItemCategory.Books,
                price = BigDecimal("12.49"),
                count = 1,
                imported = false
            ), item
        )
    }

    @Test
    fun `when the input is a book, it should parse to books`() {
        assertEquals(ItemCategory.Books, InputParser.parseItemCategory("book"))
    }

    @Test
    fun `when the input is a chocolate bar, it should parse to food`() {
        assertEquals(ItemCategory.Food, InputParser.parseItemCategory("chocolate bar"))
    }

    @Test
    fun `when the input is box of chocolates, it should parse to food`() {
        assertEquals(ItemCategory.Food, InputParser.parseItemCategory("box of chocolates"))
    }

    @Test
    fun `when the input is a music cd, it should parse to others`() {
        assertEquals(ItemCategory.Others, InputParser.parseItemCategory("music cd"))
    }

    @Test
    fun `when the input is a medical product, it should parse to medical product`() {
        assertEquals(ItemCategory.MedicalProducts, InputParser.parseItemCategory("packet of headache pills"))
    }
}
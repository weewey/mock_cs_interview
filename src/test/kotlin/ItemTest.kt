import models.Item
import models.ItemCategory
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

internal class ItemTest {
    @Test
    fun `should return the expected params`() {
        val item = Item(ItemCategory.Books, BigDecimal("1.00"), false)
        assertEquals(item.category, ItemCategory.Books)
        assertEquals(item.price, BigDecimal("1.00"))
        assertEquals(item.imported, false)
    }

}
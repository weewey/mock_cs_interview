import models.Item
import models.ItemCategory
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

internal class TaxCalculatorTest {
    @Test
    fun `when the item is an unimported book, should return the expected tax amount`() {
        val item = Item(ItemCategory.Books, BigDecimal("1.00"), false)
        assertEquals(BigDecimal("0.00"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when the item is categorized under unimported others, it should return the expected tax amount`() {
        val item = Item(ItemCategory.Others, BigDecimal("100"), false)
        assertEquals(BigDecimal("10.00"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when the item is categorized under unimported others and the price is of 2 decimal points, it should return the expected tax amount`() {
        val item = Item(ItemCategory.Others, BigDecimal("14.99"), false)
        assertEquals(BigDecimal("1.50"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when it is an unimported food item, it should return the expected tax amount`() {
        val item = Item(ItemCategory.Food, BigDecimal("10"), false)
        assertEquals(BigDecimal("0.00"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when it is an unimported medical product, it should return the expected tax amount`() {
        val item = Item(ItemCategory.MedicalProducts, BigDecimal("10"), false)
        assertEquals(BigDecimal("0.00"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when it is an imported medical product, it should return the expected tax amount`() {
        val item = Item(ItemCategory.MedicalProducts, BigDecimal("10"), true)
        assertEquals(BigDecimal("0.50"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when it is an imported good categorized as others, it should return the expected tax amount`() {
        val item = Item(ItemCategory.Others, BigDecimal("47.50"), true)
        assertEquals(BigDecimal("7.15"), TaxCalculator.calculateTax(item))
    }

    @Test
    fun `when the second decimal point is less than 5 and greater than 0, it should round up to the nearest 5`() {
        assertEquals(BigDecimal("7.15"), TaxCalculator.rounded(BigDecimal("7.13")))
        assertEquals(BigDecimal("1.55"), TaxCalculator.rounded(BigDecimal("1.51")))
    }

    @Test
    fun `when the second decimal point is 0, it should remain the same`() {
        assertEquals(BigDecimal("10.00"), TaxCalculator.rounded(BigDecimal("10.00")))
    }

    @Test
    fun `when the second decimal point is 5, it should remain the same`() {
        assertEquals(BigDecimal("1.05"), TaxCalculator.rounded(BigDecimal("1.05")))
    }

    @Test
    fun `when the second decimal point is greater than 5 and less than or eq 9, it should remain round up to nearest 10`() {
        assertEquals(BigDecimal("2.00"), TaxCalculator.rounded(BigDecimal("1.98")))
    }


}
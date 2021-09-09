import models.Item
import models.ItemCategory
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

object TaxCalculator {

    val mc = MathContext(3, RoundingMode.HALF_UP)

    fun calculateTax(item: Item): BigDecimal {
        var taxPercentage = getSalesTaxPercentage(item.category)
        if (item.imported) {
            taxPercentage += TaxAmount.ADDITIONAL_IMPORT_TAX
        }
        return calculateTaxAmountRoundedUp(item.price, taxPercentage)
    }

    private fun getSalesTaxPercentage(category: ItemCategory): BigDecimal {
        if (category != ItemCategory.Others) {
            return BigDecimal.ZERO
        }
        return TaxAmount.BASIC_SALES_TAX_AMOUNT
    }

    private fun calculateTaxAmountRoundedUp(amount: BigDecimal, taxPercentage: BigDecimal): BigDecimal {
        val taxAmount = amount.multiply(taxPercentage, mc)
        return rounded(taxAmount)
    }

    internal fun rounded(input: BigDecimal): BigDecimal {
        val tmp = input.movePointRight(2)
        val divided = tmp.divideAndRemainder(BigDecimal("10"), MathContext(0))
        val result = when (divided[1]) {
            BigDecimal("0"), BigDecimal("0.0") -> tmp
            in BigDecimal("1")..BigDecimal("5") -> divided[0].movePointRight(1).add(BigDecimal("5"))
            in BigDecimal("6")..BigDecimal("9") -> divided[0].movePointRight(1).add(BigDecimal("10"))
            else -> {
                throw Exception("${divided[1]}")
            }
        }
        return result.movePointLeft(2).setScale(2)
    }
}
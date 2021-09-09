import models.Item
import models.Receipt
import java.math.BigDecimal

object ReceiptGenerator {
    fun generate(items: List<Item>): Receipt {
        var taxes: BigDecimal = BigDecimal.ZERO
        var total: BigDecimal = BigDecimal.ZERO

        for (item in items) {
            repeat(item.count) {
                val itemTax = TaxCalculator.calculateTax(item)
                taxes += itemTax
                total += (item.price + itemTax)
            }
        }
        return Receipt(taxes, total)
    }
}
import models.Receipt

object OutputFormatter {
    fun formatOutputString(receipt: Receipt): String {
        return "Taxes: ${receipt.taxes} Total: ${receipt.total}"
    }
}
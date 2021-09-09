import models.FOOD
import models.Item
import models.ItemCategory
import models.MEDICAL_PRODUCTS

object InputParser {
    fun parse(input: String): List<Item> {
        val regex = Regex("(\\d+) ([a-zA-Z ]+) (at) ([0-9]*[.]?[0-9]+)+")
        val matches = regex.findAll(input)
        return matches.map { buildItem(it.groupValues) }.toList()
    }

    internal fun buildItem(input: List<String>): Item {
        val count = input[1].trim().toInt()
        val imported = checkImported(input[2])
        val category = parseItemCategory(input[2])
        val price = input[4].trim().toBigDecimal()
        return Item(price = price, category = category, imported = imported, count = count)
    }

    private fun checkImported(itemName: String): Boolean {
        return itemName.contains("imported", ignoreCase = true)
    }

    internal fun parseItemCategory(input: String): ItemCategory {
        val itemName = input.replace("imported", "").trim()
        return when (itemName.uppercase()) {
            in listOf("BOOK", ItemCategory.Books.toString().uppercase()) -> ItemCategory.Books
            in FOOD -> ItemCategory.Food
            in MEDICAL_PRODUCTS -> ItemCategory.MedicalProducts
            else -> ItemCategory.Others
        }
    }
}
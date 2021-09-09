package models

import java.math.BigDecimal

data class Item(val category: ItemCategory, val price: BigDecimal, val imported: Boolean, val count: Int = 1)
package models

import java.math.BigDecimal

data class Receipt(val taxes: BigDecimal, val total: BigDecimal)

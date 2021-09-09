package models

enum class ItemCategory {
    Books,
    Food,
    MedicalProducts,
    Others
}

val FOOD = listOf<String>(
    "chocolate bar".uppercase(),
    "box of chocolates".uppercase()
)

val MEDICAL_PRODUCTS = listOf<String>(
    "packet of headache pills".uppercase()
)
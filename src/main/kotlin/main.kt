import models.Item
import models.Receipt

fun main(args: Array<String>) {
    val input = "1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85"
    val outputString = execute(input)
    println(outputString)
}

fun execute(input: String): String {
    val items: List<Item> = InputParser.parse(input)
    val receipt: Receipt = ReceiptGenerator.generate(items)
    return OutputFormatter.formatOutputString(receipt)
}
package khobir.abdul.bareksa_test.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun formatPrice(number: String): String {
        val double = number.toDouble()
        return when {
            double<=1000 -> String.format("%.0f", double)
            1000<=double && double<999999 -> String.format("%.1f Ribu", double/1000).replace(".0", "")
            1000000<=double && double<999999999 -> String.format("%.1f Juta", double/1000000).replace(".0", "")
            1000000000<=double && double<999999999999 -> String.format("%.2f Miliar", double/1000000000).replace(".0", "")
            else -> String.format("%.2f Triliun", double/1000000000000).replace(".0", "")
        }
    }

    fun formatDateToMillis(date: String): Long {
        val sourceFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate: Date = sourceFormat.parse(date)
        return parsedDate.time
    }

    fun formatDate(expectedFormat: String, timeInMillis: Long): String? {
        val format = SimpleDateFormat(expectedFormat)
        return format.format(timeInMillis)
    }
}
package khobir.abdul.bareksa_test.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class XAxisValueFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return Utils.formatDate("MMM yy", value.toLong()) ?: ""
    }
}

class YAxisValueFormatter: ValueFormatter(){
    override fun getFormattedValue(value: Float): String {
        var string = ""
        string = if (value.toInt()%10==0) String.format("${value.toString().replace(".0", "")} %%") else ""
        return string
    }
}
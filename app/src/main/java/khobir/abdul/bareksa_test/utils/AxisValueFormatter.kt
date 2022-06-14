package khobir.abdul.bareksa_test.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class AxisValueFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return Utils.formatDate("MMM yy", value.toLong()) ?: ""
    }
}

class YAxisValueFormatter: ValueFormatter(){
    override fun getFormattedValue(value: Float): String {
        return String.format("$value %%")
    }
}
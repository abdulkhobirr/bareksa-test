package khobir.abdul.bareksa_test.domain.perbandingan.model

import com.google.gson.annotations.SerializedName

data class ChartDataItemModel(
    val date: String,
    val value: Float,
    val growth: Float
)

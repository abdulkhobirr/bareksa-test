package khobir.abdul.bareksa_test.data.perbandingan.model

import com.google.gson.annotations.SerializedName

data class ChartDataListResponse(
    @SerializedName("data") val listData: List<ChartDataItemResponse>,
    @SerializedName("error") val error: String
)

data class ChartDataItemResponse(
    @SerializedName("date") val date: String,
    @SerializedName("value") val value: Float,
    @SerializedName("growth") val growth: Double
)

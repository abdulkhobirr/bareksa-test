package khobir.abdul.bareksa_test.data.perbandingan.model

import com.google.gson.annotations.SerializedName

data class ChartDataResponse(
    @SerializedName("KI002MMCDANCAS00") val KI002MMCDANCAS00: ChartDataListResponse,
    @SerializedName("NI002EQCDANSIE00") val NI002EQCDANSIE00: ChartDataListResponse,
    @SerializedName("TP002EQCEQTCRS00") val TP002EQCEQTCRS00: ChartDataListResponse,
)

data class ChartDataListResponse(
    @SerializedName("error") val error: String,
    @SerializedName("data") val listData: List<ChartDataItemResponse>
)

data class ChartDataItemResponse(
    @SerializedName("date") val date: String,
    @SerializedName("value") val value: Float,
    @SerializedName("growth") val growth: Float
)

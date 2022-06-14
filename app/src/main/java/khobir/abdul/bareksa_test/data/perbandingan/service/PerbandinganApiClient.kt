package khobir.abdul.bareksa_test.data.perbandingan.service

import khobir.abdul.bareksa_test.data.BaseModel
import khobir.abdul.bareksa_test.data.SimpleResponse
import khobir.abdul.bareksa_test.data.perbandingan.model.ChartDataListResponse
import khobir.abdul.bareksa_test.data.perbandingan.model.PerbandinganDataResponse
import retrofit2.http.GET

interface PerbandinganApiClient {
    @GET("/takehometest/apps/compare/detail?productCodes=KI002MMCDANCAS00&productCodes=TP002EQCEQTCRS00&productCodes=NI002EQCDANSIE00")
    suspend fun getPerbandinganData(): SimpleResponse<BaseModel<List<PerbandinganDataResponse>>>

    @GET("/takehometest/apps/compare/chart?productCodes=KI002MMCDANCAS00&productCodes=TP002EQCEQTCRS00&productCodes=NI002EQCDANSIE00")
    suspend fun getPerbandinganChartData(): SimpleResponse<BaseModel<Map<String, ChartDataListResponse>>>
}
package khobir.abdul.bareksa_test.data.perbandingan.service

import khobir.abdul.bareksa_test.data.mapToResult
import khobir.abdul.bareksa_test.data.mapper.PerbandinganChartDataMapper
import khobir.abdul.bareksa_test.data.mapper.PerbandinganDataMapper
import khobir.abdul.bareksa_test.domain.perbandingan.PerbandinganRepository
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.utils.ResultWrapper
import java.lang.Exception

class PerbandinganRepositoryImpl(
    private val apiClient: PerbandinganApiClient,
    private val perbandinganDataMapper: PerbandinganDataMapper,
    private val perbandinganChartDataMapper: PerbandinganChartDataMapper): PerbandinganRepository {

    override suspend fun getPerbandinganData(): ResultWrapper<List<PerbandinganDataModel>> {
        return try {
            apiClient.getPerbandinganData().mapToResult(perbandinganDataMapper)
        } catch (e: Exception) {
            ResultWrapper.fail(e, "Error", e.localizedMessage)
        }
    }

    override suspend fun getPerbandinganChartData(): ResultWrapper<ChartDataModel> {
        return try {
            apiClient.getPerbandinganChartData().mapToResult(perbandinganChartDataMapper)
        } catch (e: Exception) {
            ResultWrapper.fail(e, "Error", e.localizedMessage)
        }
    }
}
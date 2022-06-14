package khobir.abdul.bareksa_test.domain.perbandingan

import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.utils.ResultWrapper

interface PerbandinganRepository {
    suspend fun getPerbandinganData(): ResultWrapper<PerbandinganDataModel>
    suspend fun getPerbandinganChartData(): ResultWrapper<ChartDataModel>
}
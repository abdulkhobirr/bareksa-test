package khobir.abdul.bareksa_test.data.mapper

import khobir.abdul.bareksa_test.data.perbandingan.model.ChartDataResponse
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataItemModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataListModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel

class PerbandinganChartDataMapper: Mapper<ChartDataResponse, ChartDataModel> {
    override fun mapFromResponse(response: ChartDataResponse): ChartDataModel {
        val mappedKI002MMCDANCAS00 = ChartDataListModel(response.KI002MMCDANCAS00.error, response.KI002MMCDANCAS00.listData.map { ChartDataItemModel(it.date, it.value, it.growth) })
        val mappedNI002EQCDANSIE00 = ChartDataListModel(response.NI002EQCDANSIE00.error, response.NI002EQCDANSIE00.listData.map { ChartDataItemModel(it.date, it.value, it.growth) })
        val mappedTP002EQCEQTCRS00 = ChartDataListModel(response.TP002EQCEQTCRS00.error, response.TP002EQCEQTCRS00.listData.map { ChartDataItemModel(it.date, it.value, it.growth) })

        return ChartDataModel(mappedKI002MMCDANCAS00, mappedNI002EQCDANSIE00, mappedTP002EQCEQTCRS00)
    }
}
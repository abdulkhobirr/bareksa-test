package khobir.abdul.bareksa_test.data.mapper

import khobir.abdul.bareksa_test.data.perbandingan.model.ChartDataListResponse
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataItemModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataListModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel

class PerbandinganChartDataMapper: Mapper<Map<String, ChartDataListResponse>, ChartDataModel> {
    override fun mapFromResponse(response: Map<String,ChartDataListResponse>): ChartDataModel {
        val map = HashMap<String, ChartDataListModel>()
        response.keys.forEach { data ->
            map[data] = ChartDataListModel(response[data]?.error ?: "", response[data]?.listData?.let { list ->
                list.map { item ->
                ChartDataItemModel(item.date, item.value, item.growth)
                }
            } ?: emptyList())
        }
        return ChartDataModel(map)
    }
}
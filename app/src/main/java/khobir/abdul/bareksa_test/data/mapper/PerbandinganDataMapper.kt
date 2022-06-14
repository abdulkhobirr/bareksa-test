package khobir.abdul.bareksa_test.data.mapper

import khobir.abdul.bareksa_test.data.perbandingan.model.PerbandinganDataResponse
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ProductDetailModel

class PerbandinganDataMapper: Mapper<List<PerbandinganDataResponse>, List<PerbandinganDataModel>> {
    override fun mapFromResponse(response: List<PerbandinganDataResponse>): List<PerbandinganDataModel> {
        return response.map {
            val details = it.details
            PerbandinganDataModel(it.codeName, it.name, ProductDetailModel(
                details.category,
                details.categoryId,
                details.currency,
                details.custody,
                details.inceptionDate,
                details.avatarUrl,
                details.imName,
                details.minBalance,
                details.minRedemption,
                details.minSubscription,
                details.nav,
                details.returnCurrentYear,
                details.returnFiveYear,
                details.returnFourYear,
                details.returnInceptionGrowth,
                details.returnOneDay,
                details.returnOneMonth,
                details.returnOneWeek,
                details.returnOneYear,
                details.returnSixMonth,
                details.returnThreeMonth,
                details.returnThreeYear,
                details.returnTwoYear,
                details.totalUnit,
                details.type,
                details.typeId))
        }
    }
}
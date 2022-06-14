package khobir.abdul.bareksa_test.data.mapper

import khobir.abdul.bareksa_test.data.perbandingan.model.PerbandinganDataResponse
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.ProductDetailModel

class PerbandinganDataMapper: Mapper<PerbandinganDataResponse, PerbandinganDataModel> {
    override fun mapFromResponse(response: PerbandinganDataResponse): PerbandinganDataModel {
        val mappedDetail = ProductDetailModel(
            response.details.category,
            response.details.categoryId,
            response.details.currency,
            response.details.custody,
            response.details.inceptionDate,
            response.details.avatarUrl,
            response.details.imName,
            response.details.minBalance,
            response.details.minRedemption,
            response.details.minSubscription,
            response.details.nav,
            response.details.returnCurrentYear,
            response.details.returnFiveYear,
            response.details.returnFourYear,
            response.details.returnInceptionGrowth,
            response.details.returnOneDay,
            response.details.returnOneMonth,
            response.details.returnOneWeek,
            response.details.returnOneYear,
            response.details.returnSixMonth,
            response.details.returnThreeMonth,
            response.details.returnThreeYear,
            response.details.returnTwoYear,
            response.details.totalUnit,
            response.details.type,
            response.details.typeId)
        return PerbandinganDataModel(response.codeName, response.name, mappedDetail)
    }
}
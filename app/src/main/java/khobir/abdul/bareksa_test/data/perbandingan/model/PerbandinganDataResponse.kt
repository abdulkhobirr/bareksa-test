package khobir.abdul.bareksa_test.data.perbandingan.model

import com.google.gson.annotations.SerializedName

data class PerbandinganDataResponse(
    @SerializedName("code") val codeName: String,
    @SerializedName("name") val name: String,
    @SerializedName("details") val details: ProductDetailResponse,
)

data class ProductDetailResponse(
    @SerializedName("category") val category: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("custody") val custody: String,
    @SerializedName("inception_date") val inceptionDate: String,
    @SerializedName("im_avatar") val avatarUrl: String,
    @SerializedName("im_name") val imName: String,
    @SerializedName("min_balance") val minBalance: String,
    @SerializedName("min_redemption") val minRedemption: String,
    @SerializedName("min_subscription") val minSubscription: String,
    @SerializedName("nav") val nav: Float,
    @SerializedName("return_cur_year") val returnCurrentYear: String,
    @SerializedName("return_five_year") val returnFiveYear: String,
    @SerializedName("return_four_year") val returnFourYear: String,
    @SerializedName("return_inception_growth") val returnInceptionGrowth: String,
    @SerializedName("return_one_day") val returnOneDay: String,
    @SerializedName("return_one_month") val returnOneMonth: String,
    @SerializedName("return_one_week") val returnOneWeek: String,
    @SerializedName("return_one_year") val returnOneYear: String,
    @SerializedName("return_six_month") val returnSixMonth: String,
    @SerializedName("return_three_month") val returnThreeMonth: String,
    @SerializedName("return_three_year") val returnThreeYear: String,
    @SerializedName("return_two_year") val returnTwoYear: String,
    @SerializedName("total_unit") val totalUnit: String,
    @SerializedName("type") val type: String,
    @SerializedName("type_id") val typeId: String,
)

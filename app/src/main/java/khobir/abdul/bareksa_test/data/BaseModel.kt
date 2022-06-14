package khobir.abdul.bareksa_test.data

import com.google.gson.annotations.SerializedName

data class BaseModel<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("error") val error: String,
    @SerializedName("data") val `data`: T
)

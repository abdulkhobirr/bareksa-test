package khobir.abdul.bareksa_test.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun <S> createRetrofitService(
        serviceClass: Class<S>,
        okhttpClient: OkHttpClient,
        baseURl: String
    ): S {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }
}
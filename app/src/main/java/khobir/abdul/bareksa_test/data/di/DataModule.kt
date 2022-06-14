package khobir.abdul.bareksa_test.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import khobir.abdul.bareksa_test.BuildConfig
import khobir.abdul.bareksa_test.data.mapper.PerbandinganChartDataMapper
import khobir.abdul.bareksa_test.data.mapper.PerbandinganDataMapper
import khobir.abdul.bareksa_test.data.perbandingan.service.PerbandinganApiClient
import khobir.abdul.bareksa_test.data.perbandingan.service.PerbandinganRepositoryImpl
import khobir.abdul.bareksa_test.domain.perbandingan.PerbandinganRepository
import khobir.abdul.bareksa_test.utils.OkHttpClientFactory
import khobir.abdul.bareksa_test.utils.RetrofitFactory
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClientFactory.create(showDebugLog = true, chuckerInterceptor =  chuckerInterceptor)
    }

    @Provides
    @Singleton
    fun provideApiClient(okHttpClient: OkHttpClient, @Named("BaseUrl") baseUrl: String): PerbandinganApiClient {
        return RetrofitFactory.createRetrofitService(
            PerbandinganApiClient::class.java,
            okHttpClient, baseUrl
        )
    }

    @Provides
    @Singleton
    fun providePerbandinganRepository(apiClient: PerbandinganApiClient, dataMapper: PerbandinganDataMapper,
                                       chartDataMapper: PerbandinganChartDataMapper): PerbandinganRepository {
        return PerbandinganRepositoryImpl(apiClient, dataMapper, chartDataMapper)
    }

    @Provides
    @Singleton
    fun providePerbandinganDataMapper(): PerbandinganDataMapper {
        return PerbandinganDataMapper()
    }

    @Provides
    @Singleton
    fun providePerbandinganChartDataMapper(): PerbandinganChartDataMapper {
        return PerbandinganChartDataMapper()
    }
}
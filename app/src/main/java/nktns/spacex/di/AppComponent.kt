package nktns.spacex.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import nktns.spacex.data.SpaceXApi
import nktns.spacex.vehicles.MyViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Component(modules = [AppModule::class])
interface AppComponent {
    fun viewModelsFactory(): MyViewModelFactory
}

@Module(includes = [NetworkModule::class])
class AppModule

@Module
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideSpaceXApi(): SpaceXApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create()
    }
}

package nktns.spacex.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import nktns.spacex.data.SpaceXApi
import nktns.spacex.data.database.SpaceXDatabase
import nktns.spacex.data.database.launches.LaunchDAO
import nktns.spacex.data.database.vehicles.dragons.DragonDAO
import nktns.spacex.data.database.vehicles.rockets.RocketDAO
import nktns.spacex.data.database.vehicles.ships.ShipDAO
import nktns.spacex.ui.company.CompanyVMFactory
import nktns.spacex.ui.launches.LaunchesVMFactory
import nktns.spacex.ui.vehicles.vehicleList.VehiclesVMFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun vehiclesVMFactory(): VehiclesVMFactory
    fun companyVMFactory(): CompanyVMFactory
    fun launchesVMFactory(): LaunchesVMFactory
}

@Module(includes = [AppModule.NetworkModule::class, AppModule.RoomModule::class])
class AppModule {

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("file", Context.MODE_PRIVATE)
    }

    @Module
    class NetworkModule {

        private val json = Json { ignoreUnknownKeys = true }

        @Provides
        fun provideInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        @Provides
        fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
            return okHttpClient.build()
        }

        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v4/")
                .client(okHttpClient)
                .addConverterFactory(json.asConverterFactory(contentType))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @Provides
        fun provideSpaceXApi(retrofit: Retrofit): SpaceXApi {
            return retrofit.create(SpaceXApi::class.java)
        }
    }

    @Module
    class RoomModule {

        @Provides
        fun provideSpaceXDatabase(context: Context): SpaceXDatabase {
            return Room.databaseBuilder(context, SpaceXDatabase::class.java, "database").build()
        }

        @Provides
        fun provideLaunchDAO(database: SpaceXDatabase): LaunchDAO {
            return database.launchDAO()
        }

        @Provides
        fun provideRocketDAO(database: SpaceXDatabase): RocketDAO {
            return database.rocketDAO()
        }

        @Provides
        fun provideDragonDAO(database: SpaceXDatabase): DragonDAO {
            return database.dragonDAO()
        }

        @Provides
        fun provideShipDAO(database: SpaceXDatabase): ShipDAO {
            return database.shipDAO()
        }
    }
}

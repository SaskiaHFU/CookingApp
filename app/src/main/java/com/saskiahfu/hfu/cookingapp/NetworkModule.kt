package com.saskiahfu.hfu.cookingapp

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWebService(
//        userSettingsRepository: UserSettingsRepository,
    ): WebService {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor {
                            Log.d("OKHTTP", it)
                        }.apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
//                    .addInterceptor { chain ->
//                        val credentials = runBlocking {
//                            when (val state = userSettingsRepository.getSettings().loginState) {
//                                is LoginState.LoggedIn -> state.credentials
//                                LoginState.LoggedOut -> ""
//                                is LoginState.LoggingIn -> state.credentials
//                            }
//                        }
//                        val request = chain.request().newBuilder()
//                            .addHeader("Authorization", "Basic $credentials")
//                            .build()
//                        chain.proceed(request)
//                    }
                    .build()
            )
            .baseUrl(WebService.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(WebService::class.java)
    }
}

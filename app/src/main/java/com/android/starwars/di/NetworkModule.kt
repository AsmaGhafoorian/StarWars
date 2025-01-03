package com.android.starwars.di

import com.android.starwars.data.ApiServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val BASE_URL = "https://swapi.py4e.com/api/"
    @Provides
    @Singleton
    fun provideOkHttpProvider(
        interceptor: Interceptor
    ): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
//        }

        clientBuilder.interceptors().add(interceptor)
        clientBuilder.connectTimeout(30L, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30L, TimeUnit.SECONDS)
        clientBuilder.readTimeout(30L, TimeUnit.SECONDS)
        return clientBuilder.build()

    }

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi,): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices {

        return retrofit.create(ApiServices::class.java)

    }
}
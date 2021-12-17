package com.andrew.movieapp.di

import com.andrew.movieapp.BuildConfig
import com.andrew.movieapp.model.MoviesApi
import com.andrew.movieapp.model.MoviesService
import com.andrew.movieapp.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.HttpUrl
import okhttp3.Request


@Module
class ApiModule {

    @Provides
    fun provideMoviesApi(): MoviesApi{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            println("URL: $url")

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    fun provideMoviesService(): MoviesService {
        return MoviesService()
    }
}
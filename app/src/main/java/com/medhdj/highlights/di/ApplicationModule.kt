package com.medhdj.highlights.di

import com.medhdj.features.highlights.data.FootballHighlightsRepository
import com.medhdj.features.highlights.data.HighlightsApi
import com.medhdj.highlights.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HIGHLIGHTS_ENDPOINT)
            .client(createOktthp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOktthp(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            build()
        }
    }

    @Provides
    @Singleton
    fun provideFootballHighlightsRepository(network: FootballHighlightsRepository.Network):
            FootballHighlightsRepository = network

    @Singleton
    @Provides
    fun provideHighlightsApi(retrofit: Retrofit): HighlightsApi =
        retrofit.create(HighlightsApi::class.java)

    companion object {
        const val HIGHLIGHTS_ENDPOINT = "https://www.scorebat.com/video-api/v3/"
    }
}
package com.discover.soulswipe.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.discover.soulswipe.BuildConfig
import com.discover.soulswipe.core.network.ApiClient
import com.discover.soulswipe.data.remote.RemoteDataSource
import com.discover.soulswipe.data.repository.LoginRepositoryImpl
import com.discover.soulswipe.data.repository.NotesRepositoryImpl
import com.discover.soulswipe.domain.repository.LoginRepository
import com.discover.soulswipe.domain.repository.NotesRepository
import com.discover.soulswipe.domain.usecase.LoginUseCase
import com.discover.soulswipe.domain.usecase.NotesUseCase

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient).addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        remote: RemoteDataSource,
        appContext: Application
    ): LoginRepository {
        return LoginRepositoryImpl(remote, appContext)
    }


    @Singleton
    @Provides
    fun provideNotesUseCase(repository: NotesRepository): NotesUseCase {
        return NotesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideNotesRepository(
        remote: RemoteDataSource,
        appContext: Application
    ): NotesRepository {
        return NotesRepositoryImpl(remote, appContext)
    }

    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
}
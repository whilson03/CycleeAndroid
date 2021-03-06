package com.olabode.wilson.cyclee.feature_authentication.di

import com.olabode.wilson.cyclee.feature_authentication.data.AuthApiService
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.utils.timer.VerificationCountDownTimer
import com.olabode.wilson.cyclee.feature_authentication.utils.timer.VerificationCountDownTimerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 16/11/2021
 * EMAIL: whilson03@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit.Builder): AuthApiService {
        return retrofit.build().create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideVerificationTimer(): VerificationCountDownTimer {
        return VerificationCountDownTimerImpl(millis = AuthConstants.TWO_MINUTES_IN_MILLIS)
    }
}

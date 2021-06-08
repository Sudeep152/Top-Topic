package com.shashank.toptopic.di

import com.shashank.toptopic.repository.DefaultAuthRepository
import com.shashank.toptopic.repository.DefaultMainRepository
import com.shashank.toptopic.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @ActivityScoped
    @Provides
    fun getRepository() = DefaultMainRepository() as MainRepository
}
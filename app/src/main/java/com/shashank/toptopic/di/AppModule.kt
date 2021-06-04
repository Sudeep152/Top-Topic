package com.shashank.toptopic.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shashank.toptopic.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
      fun provideApplicationContext(@ApplicationContext context: Context) =
          context


    @Singleton
    @Provides
    fun getDispatcher()=Dispatchers.Main as CoroutineDispatcher


    @Singleton
    @Provides
     fun provideGlideInstance (
    @ApplicationContext context: Context

     )=Glide.with(context).setDefaultRequestOptions(
         RequestOptions()
             .placeholder(R.drawable.ic_baseline_person_24)
             .error(R.drawable.ic_baseline_error_24)
             .diskCacheStrategy(DiskCacheStrategy.DATA)

     )
}
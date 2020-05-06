package com.lonnie.chicsign.myfavoritesongs.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {
    @Named("hello")
    @Provides
    fun provideHelloWorld() = "Hello World!!!"
}
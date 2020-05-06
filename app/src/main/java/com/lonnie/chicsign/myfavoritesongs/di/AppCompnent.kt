package com.lonnie.chicsign.myfavoritesongs.di

import com.lonnie.chicsign.myfavoritesongs.MyApplication
import com.lonnie.chicsign.myfavoritesongs.di.modules.ActivityModule
import com.lonnie.chicsign.myfavoritesongs.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityModule::class), (AppModule::class)])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}

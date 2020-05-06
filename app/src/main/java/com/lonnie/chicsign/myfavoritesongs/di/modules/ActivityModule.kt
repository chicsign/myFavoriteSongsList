package com.lonnie.chicsign.myfavoritesongs.di.modules

import com.lonnie.chicsign.myfavoritesongs.di.modules.main.MainActivityModule
import com.lonnie.chicsign.myfavoritesongs.di.scope.ActivityScope
import com.lonnie.chicsign.myfavoritesongs.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun getMainActivity(): MainActivity
}
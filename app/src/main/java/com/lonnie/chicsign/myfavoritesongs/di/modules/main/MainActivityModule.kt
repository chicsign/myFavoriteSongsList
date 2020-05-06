package com.lonnie.chicsign.myfavoritesongs.di.modules.main

import androidx.databinding.DataBindingUtil
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.databinding.ActivityMainBinding
import com.lonnie.chicsign.myfavoritesongs.di.scope.ActivityScope
import com.lonnie.chicsign.myfavoritesongs.di.scope.FragmentScope
import com.lonnie.chicsign.myfavoritesongs.view.MainActivity
import com.lonnie.chicsign.myfavoritesongs.view.MainFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule{
    @Module
    companion object{
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideMainActivityBinding(activity: MainActivity): ActivityMainBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_main)
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [(MainFragmentModule::class)])
    abstract fun getMainFragment(): MainFragment
}
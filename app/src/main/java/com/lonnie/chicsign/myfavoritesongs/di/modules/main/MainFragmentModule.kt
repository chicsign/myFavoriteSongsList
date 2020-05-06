package com.lonnie.chicsign.myfavoritesongs.di.modules.main

import androidx.databinding.DataBindingUtil
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.databinding.MainFragmentBinding
import com.lonnie.chicsign.myfavoritesongs.di.scope.FragmentScope
import com.lonnie.chicsign.myfavoritesongs.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule{
    @Provides
    @FragmentScope
    fun provideMainFragmentBinding(activity: MainActivity): MainFragmentBinding {
        return DataBindingUtil.inflate<MainFragmentBinding>(
            activity.layoutInflater,
            R.layout.main_fragment,
            null,
            false
        )
    }
}
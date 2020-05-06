package com.lonnie.chicsign.myfavoritesongs.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.databinding.ActivityMainBinding
import com.lonnie.chicsign.myfavoritesongs.databinding.MainFragmentBinding
import com.lonnie.chicsign.myfavoritesongs.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment @Inject constructor() : DaggerFragment() {
    @Inject
    lateinit var binding: MainFragmentBinding

    val adapter by lazy {
        RecyclerAdapter()
    }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )

        floatingActionButton.setOnClickListener {
            val intent = Intent(activity, WriteActivity::class.java)
            startActivity(intent)
        }


        observerData()

    }

    private fun observerData() {
        viewModel.fetchInfo().observe(this, Observer {
            if (adapter != null) {
                adapter.setListData(it)
                adapter.notifyDataSetChanged()
            }

        })
    }


}

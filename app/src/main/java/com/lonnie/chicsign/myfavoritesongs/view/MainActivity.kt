package com.lonnie.chicsign.myfavoritesongs.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.databinding.ActivityMainBinding
import com.lonnie.chicsign.myfavoritesongs.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapter = RecyclerAdapter(this)

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        observerData()
        updateUI()
        binding.mSwipeRefresh.setOnRefreshListener {
            observerData()
            if(binding.mSwipeRefresh.isRefreshing)
                binding.mSwipeRefresh.isRefreshing = false
        }
    }

    private fun observerData() {
        viewModel.fetchInfo().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
            updateUI()
        })
    }

    private fun updateUI(){
        if(adapter.isEmptyListItem())
            txtEmptyList.visibility = View.VISIBLE
        else
            txtEmptyList.visibility = View.GONE
    }
}


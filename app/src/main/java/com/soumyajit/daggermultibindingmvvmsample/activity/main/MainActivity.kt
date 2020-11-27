package com.soumyajit.daggermultibindingmvvmsample.activity.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.soumyajit.daggermultibindingmvvmsample.R
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import com.soumyajit.daggermultibindingmvvmsample.showToast
import com.soumyajit.daggermultibindingmvvmsample.recyclerview.StarWarsPeopleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    lateinit var adapter : StarWarsPeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewState()
        observeList()
    }

    private fun observeList() {
        mainActivityViewModel.list.observe(this, Observer { list ->
            list?.let { peopleList ->
                if(peopleList.isNotEmpty()) {
                    showData(peopleList)
                }
            }
        })
    }

    private fun observeViewState() {
        mainActivityViewModel.state.observe(this,  Observer { state ->
            when(state){
                is MainActivityViewState.ShowLoading -> {
                    initialUiState()
                    showLoading()
                }
                is MainActivityViewState.ShowError -> {
                    showError(state.error)
                }
            }
        })
    }

    private fun initialUiState(){
        progress_circular.visibility = View.GONE
        recyclerview.visibility = View.GONE
        adapter = StarWarsPeopleAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
    }

    private fun showLoading(){
        progress_circular.visibility = View.VISIBLE
    }

    private fun showData(data: PagedList<StarWarsPeople>) {
        removeProgressDialog()
        recyclerview.visibility = View.VISIBLE
        adapter.submitList(data)
    }

    private fun showError(error: Throwable) {
        removeProgressDialog()
        showToast(error.message, Toast.LENGTH_LONG)
    }

    private fun removeProgressDialog() {
        progress_circular.visibility = View.GONE
    }
}

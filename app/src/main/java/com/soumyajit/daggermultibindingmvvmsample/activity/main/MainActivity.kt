package com.soumyajit.daggermultibindingmvvmsample.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soumyajit.daggermultibindingmvvmsample.Factory.ViewModelFactory
import com.soumyajit.daggermultibindingmvvmsample.R
import com.soumyajit.daggermultibindingmvvmsample.Users
import com.soumyajit.daggermultibindingmvvmsample.activity.SampleActivity
import com.soumyajit.daggermultibindingmvvmsample.activity.showToast
import com.soumyajit.daggermultibindingmvvmsample.recyclerview.UsersAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
class MainActivity : DaggerAppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val mainActivityViewModel : MainActivityViewModel by viewModels{ viewModelFactory }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var adapter : UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startActivity(Intent(this, SampleActivity::class.java))
        }
        //observeViewStateWithoutPrecaution()
        //observeViewStateWithPrecaution()
        observeViewStateAsLiveData()
    }

    private fun observeViewStateWithoutPrecaution() {
        lifecycleScope.launch {
            mainActivityViewModel.state.collect { uiState ->
                when(uiState) {
                    is MainActivityViewState.ShowLoading -> {
                        initialUiState()
                        showLoading()
                    }
                    is MainActivityViewState.ShowError -> {
                        showError(uiState.error)
                    }
                    is MainActivityViewState.ShowData -> {
                        Log.i(TAG, "data in UI")
                        showData(uiState.data)
                    }
                }
            }
        }
    }

    private fun observeViewStateWithPrecaution() {
        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.state.collect { uiState ->
                when(uiState) {
                    is MainActivityViewState.ShowLoading -> {
                        initialUiState()
                        showLoading()
                    }
                    is MainActivityViewState.ShowError -> {
                        showError(uiState.error)
                    }
                    is MainActivityViewState.ShowData -> {
                        Log.i(TAG, "data in UI")
                        showData(uiState.data)
                    }
                }
            }
        }
    }

    private fun observeViewStateAsLiveData() {
        mainActivityViewModel.state.asLiveData().observe(this,  Observer { state ->
            when(state){
                is MainActivityViewState.ShowLoading -> {
                    initialUiState()
                    showLoading()
                }
                is MainActivityViewState.ShowError -> {
                    showError(state.error)
                }
                is MainActivityViewState.ShowData -> {
                    Log.i(TAG, "data in UI")
                    showData(state.data)
                }
            }
        })
    }

    private fun initialUiState(){
        progress_circular.visibility = View.GONE
        recyclerview.visibility = View.GONE
        adapter = UsersAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
    }

    private fun showLoading(){
        progress_circular.visibility = View.VISIBLE
    }

    private fun showData(data: List<Users>) {
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

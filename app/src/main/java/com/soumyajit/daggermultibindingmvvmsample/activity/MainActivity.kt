package com.soumyajit.daggermultibindingmvvmsample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.soumyajit.daggermultibindingmvvmsample.Factory.ViewModelFactory
import com.soumyajit.daggermultibindingmvvmsample.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var mainActivityViewModel : MainActivityViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainActivityViewModel::class.java)
        mainActivityViewModel.dataResponse.observe(this,  Observer {
            progress_circular.visibility = View.GONE
        })

    }
}

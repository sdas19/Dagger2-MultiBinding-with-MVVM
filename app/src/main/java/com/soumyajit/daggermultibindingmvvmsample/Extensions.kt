package com.soumyajit.daggermultibindingmvvmsample

import android.app.Activity
import android.widget.Toast

fun Activity.showToast(message: String?, duration: Int){
    message?.let {
        Toast.makeText(this, it, duration).show()
    }
}

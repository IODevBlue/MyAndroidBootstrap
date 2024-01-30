package com.blueiobase.app.android.shopper.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach

fun ViewGroup.forEachView(run: View.()-> Unit) {
    this.forEach { view -> view.run()
        if(view is ViewGroup) view.forEachView { run() }
    }
}
package com.apps.blueiobase.distress.utils

import android.util.Log

 object Logger {
        fun noteLog(message: String) {
            Log.e("NOTE", message)
        }
        fun warningLog(message: String) {
            Log.e("WARNING", message)

        }
 }

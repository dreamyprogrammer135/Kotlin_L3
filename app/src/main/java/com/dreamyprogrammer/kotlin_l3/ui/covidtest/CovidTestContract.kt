package com.dreamyprogrammer.kotlin_l3.ui.covidtest

import android.content.Context
import androidx.annotation.WorkerThread

interface CovidTestContract {
    interface View {
        @WorkerThread
        fun showResult(result: String)
        @WorkerThread
        fun clearResult()
        @WorkerThread
        fun showProgress()
        @WorkerThread
        fun hideProgress()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onStartEvaluation(igg: Float, igm: Float, pcr: Boolean)
    }
}
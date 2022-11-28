package com.dreamyprogrammer.kotlin_l3.ui.covidtest

import android.os.Handler
import android.os.Looper
import com.dreamyprogrammer.kotlin_l3.CovidTestEvaluator
import com.dreamyprogrammer.kotlin_l3.IggCovidTestEvaluatorImpl
import com.dreamyprogrammer.kotlin_l3.IgmCovidTestEvaluatorImpl
import com.dreamyprogrammer.kotlin_l3.PcrCovidTestEvaluatorImpl

class CovidTestPresenter : CovidTestContract.Presenter {
    private var view: CovidTestContract.View? = null

    private val iggCovidTestEvaluatorImpl = IggCovidTestEvaluatorImpl()
    private val igmCovidTestEvaluatorImpl = IgmCovidTestEvaluatorImpl()
    private val pcrCovidTestEvaluatorImpl = PcrCovidTestEvaluatorImpl()

    private var iggStatus: String? = null
    private var igmStatus: String? = null
    private var pcrStatus: String? = null
    private var mainHandler: Handler? = null

    override fun attach(view: CovidTestContract.View) {
        this.view = view
        mainHandler = Handler(Looper.getMainLooper())
    }

    override fun detach() {
        view = null
        mainHandler = null
    }

    override fun onStartEvaluation(igg: Float, igm: Float, pcr: Boolean) {
        view?.clearResult()
        view?.showProgress()
        Thread {
            iggStatus = "\nIgG: ${
                iggCovidTestEvaluatorImpl.evaluate(igg).statusToString()
            }"
            printIfPossible()
        }.start()
        Thread {
            igmStatus = "\nIgM: ${
                igmCovidTestEvaluatorImpl.evaluate(igm).statusToString()
            }"
            printIfPossible()
        }.start()
        Thread {
            pcrStatus = "\nPcr: ${
                pcrCovidTestEvaluatorImpl.evaluate(pcr).statusToString()
            }"
            printIfPossible()
        }.start()

//            sb.append("\nIgG: ")
//            sb.append(
//                iggCovidTestEvaluatorImpl.evaluate(
//                        binding.iggInputEditText.text.toString().toFloat()
//                    ).statusToString()
//            )
//            sb.append("\nIgM: ")
//            sb.append(
//                (
//                        igmCovidTestEvaluatorImpl.evaluate(
//                            binding.igmInputEditText.text.toString().toFloat()
//                        )
//                        ).statusToString()
//            )
    }

    private fun printIfPossible() {
        mainHandler?.post {
            if (canPrint()) {
                view?.hideProgress()
                view?.showResult("$iggStatus\n$igmStatus\n$pcrStatus")
                iggStatus = null
                igmStatus = null
                pcrStatus = null
            }
        }
    }

    private fun canPrint(): Boolean =
        !iggStatus.isNullOrEmpty() && !igmStatus.isNullOrEmpty() && !pcrStatus.isNullOrEmpty()

    private fun CovidTestEvaluator.CovidStatus.statusToString(): String = when (this) {
        CovidTestEvaluator.CovidStatus.NEGATIVE -> "Отрицательно"
        CovidTestEvaluator.CovidStatus.POSITIVE -> "Положительно"
        CovidTestEvaluator.CovidStatus.UNRELIABLE -> "Хз"
    }

}
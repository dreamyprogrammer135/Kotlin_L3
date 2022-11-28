package com.dreamyprogrammer.kotlin_l3

class PcrCovidTestEvaluatorImpl : CovidTestEvaluator<Boolean> {
    override fun evaluate(value: Boolean): CovidTestEvaluator.CovidStatus =
        if (value) CovidTestEvaluator.CovidStatus.POSITIVE else CovidTestEvaluator.CovidStatus.NEGATIVE
}
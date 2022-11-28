package com.dreamyprogrammer.kotlin_l3

class IggCovidTestEvaluatorImpl : CovidTestEvaluator<Float> {
    override fun evaluate(value: Float): CovidTestEvaluator.CovidStatus {
        Thread.sleep(3000)
        return when {
            value in 0f..0.95f -> CovidTestEvaluator.CovidStatus.NEGATIVE
            value in 0.95f..1.4f -> CovidTestEvaluator.CovidStatus.UNRELIABLE
            value > 1.4 -> CovidTestEvaluator.CovidStatus.POSITIVE
            else -> throw CovidTestEvaluator.EvaluatorException("Антител меньше нуля?")
        }
    }

}
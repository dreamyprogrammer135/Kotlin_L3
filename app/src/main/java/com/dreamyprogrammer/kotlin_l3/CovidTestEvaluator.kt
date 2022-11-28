package com.dreamyprogrammer.kotlin_l3

//const val POSITIVE = 1
//const val NEGATIVE = 2
//const val UNRELIABLE = 0

interface CovidTestEvaluator<T> {
    enum class CovidStatus(val humanReadableString: String) {
        POSITIVE("болен"), NEGATIVE("здоров"), UNRELIABLE("хз")
    }

    class EvaluatorException(message: String): Throwable(message)

    @Throws(EvaluatorException::class)
    fun evaluate(value: T): CovidStatus
}


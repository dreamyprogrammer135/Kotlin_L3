package com.dreamyprogrammer.kotlin_l3;

import androidx.annotation.NonNull;

public class IgmCovidTestEvaluatorImpl implements CovidTestEvaluator<Float> {

    @NonNull
    @Override
    public CovidStatus evaluate(Float value) throws EvaluatorException {
        if (0 <= value && value < 0.95f) {
            return CovidStatus.NEGATIVE;
        } else if (0.95f <= value && value < 1.4) {
            return CovidStatus.UNRELIABLE;
        } else if (1.4f <= value) {
            return CovidStatus.POSITIVE;
        } else {
            throw new EvaluatorException("Значерие не может быть меньше нуля");
        }
    }
}

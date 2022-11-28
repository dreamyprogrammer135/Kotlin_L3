package com.dreamyprogrammer.kotlin_l3.ui.covidtest

import android.os.Bundle
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dreamyprogrammer.kotlin_l3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CovidTestContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CovidTestContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = lastCustomNonConfigurationInstance as? CovidTestContract.Presenter
            ?: CovidTestPresenter()
        presenter.attach(this)

        initView()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    private fun initView() {
        binding.resultButton.setOnClickListener {
            presenter.onStartEvaluation(
                binding.iggInputEditText.text.toString().toFloat(),
                binding.igmInputEditText.text.toString().toFloat(),
                binding.pcrCheckBox.isChecked
            )
        }
    }

    @WorkerThread
    override fun showResult(result: String) {
        runOnUiThread {
            binding.resultTextView.isVisible = true
            binding.resultTextView.text = result
        }
    }

    @WorkerThread
    override fun clearResult() {
        runOnUiThread {
            binding.resultTextView.isVisible = false
        }
    }

    @WorkerThread
    override fun showProgress() {
        runOnUiThread {
            binding.progressBar.isVisible = true
        }
    }

    @WorkerThread
    override fun hideProgress() {
        runOnUiThread {
            binding.progressBar.isVisible = false
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}

fun loopIteration() {
    val index = 4
    val name = "Вася"
    val textToPrint = "Имя: " + "\nПорядковый номер: " + index
    val textToPrint2 = String.format("Имя: %s\nПорядковый номер: %d", name, index)
    val textToPrintKt = "Имя: ${name.uppercase()} \nПорядковый номер: $index"
}

fun foo() {
    val name = "Всем привет в этм чатике".goTo2007()
}

private fun String.goTo2007(): String {
    val sb = StringBuilder()
    var switch = false
    this.forEach {
        sb.append(if (switch) it else it.uppercase())
        switch = !switch;
    }
    return sb.toString()
}




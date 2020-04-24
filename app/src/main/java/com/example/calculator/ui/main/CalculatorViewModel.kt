package com.example.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.core.io
import com.example.calculator.core.ui
import com.example.calculator.domain.model.OutputValue
import com.example.calculator.domain.usecase.ProcessResultUsecase
import com.example.calculator.ui.model.OutputUi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CalculatorViewModel(val processResultUsecase: ProcessResultUsecase) : ViewModel() {
    private var result: MutableLiveData<OutputUi> = MutableLiveData()

    fun setDisplayState(input: String) {
        result.value = OutputUi(result?.value?.output + input)
    }
    init {
        clear()
    }
    fun clear() {
        result.value = OutputUi("")
    }

    val viewState: LiveData<OutputUi>
        get() = result


    fun processResult() {
        viewModelScope.launch {
            io {
                processResultUsecase.execute(result.value?.output ?: "").collect {
                    ui {
                        when (it) {
                            is OutputValue.SuccessOutput -> {
                                result.value = OutputUi(it.result)
                            }
                            is OutputValue.ErrorOutput -> {
                                result.value = OutputUi(it.result)
                            }
                        }
                    }
                }
            }
        }
    }

}

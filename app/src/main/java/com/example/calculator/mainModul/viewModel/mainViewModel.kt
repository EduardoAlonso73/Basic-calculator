package com.example.calculator.mainModul.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.common.entities.OperationEntity
import com.example.calculator.mainModul.model.MainRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class mainViewModel : ViewModel() {

    var resultOperation: MutableLiveData<String> = MutableLiveData()
    var backValueExpression: MutableLiveData<String> = MutableLiveData()
    var mgsErrorExpression: MutableLiveData<String> = MutableLiveData()
    var fontSizeValueResul:MutableLiveData<Int> =MutableLiveData()
    var fontSizeValueExpre:MutableLiveData<Int> =MutableLiveData()
    private val interaction = MainRepository()

    fun setFontSizeValueResul(size:Int){ fontSizeValueResul.value=size}
    fun setFontSizeValueExpre(size:Int){ fontSizeValueExpre.value=size}


    fun calculateExpression(expressionValue: String) {
        try {
            val expression = ExpressionBuilder(expressionValue).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            val resul =if (result == longResult.toDouble()) longResult.toString() else longResult.toString()
            resultOperation.value = resul


            fontSizeValueExpre.value=20
            fontSizeValueResul.value=60
            val operationAdding=OperationEntity(
                operationExpr = expressionValue,
                resultOperation = resul)
            saveOperation(operationAdding)

        } catch (e: Exception) {
            mgsErrorExpression.value = e.message.toString()
        }
    }


    fun backValue(valueString: String) {
        if (valueString.isNotEmpty()) {
            backValueExpression.value = valueString.substring(0, valueString.length - 1)
        }
        resultOperation.value = ""

    }

    fun clearValue() {
        backValueExpression.value = ""
        resultOperation.value = ""
        setFontSizeValueResul(40)
        setFontSizeValueExpre(50)
    }

    fun getAllOperations(): LiveData<MutableList<OperationEntity>> =interaction.getAllOperations()

   private fun saveOperation(operation: OperationEntity) {
        executeAction() {interaction.addOperation(operation)}
    }



    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()

            } catch (e: Exception) {
                println(e.message)
            }

        }
    }


}
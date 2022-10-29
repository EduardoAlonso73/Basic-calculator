package com.example.calculator.mainModul.view

import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.common.entities.OperationEntity
import com.example.calculator.common.utils.BottomSheetMenu
import com.example.calculator.common.utils.utlils
import com.example.calculator.mainModul.viewModel.mainViewModel
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.mainModul.viewModel.adapter.OnClickListenerAct
import com.example.calculator.mainModul.viewModel.adapter.OperationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), OnClickListenerAct {
    private lateinit var mBinding: ActivityMainBinding
    private val mainViewModel: mainViewModel by viewModels()
    private lateinit var operationList: MutableList<OperationEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupViewModel()
        pressValue()
        listEvents()
    }

    private fun setupViewModel() {
        mainViewModel.resultOperation.observe(this) {
            mBinding.tvResult.text = it
        }
        mainViewModel.backValueExpression.observe(this) {
            mBinding.tvExpression.text = it
        }
        mainViewModel.mgsErrorExpression.observe(this) {
            val mgs = if (it == "null") {
                getString(R.string.mgs_error)
            } else {
                it
            }
            Snackbar.make(mBinding.root, mgs, Snackbar.LENGTH_SHORT).show()
        }

        mainViewModel.fontSizeValueResul.observe(this) {
            mBinding.tvResult.textSize = it.toFloat()
        }
        mainViewModel.fontSizeValueExpre.observe(this) {
            mBinding.tvExpression.textSize = it.toFloat()
        }
        mainViewModel.getAllOperations().observe(this) {
            operationList = it
        }
    }


    private fun showHistories() {
        BottomSheetMenu(this, this@MainActivity, operationList).show()
    }

    private fun pressValue() {
        with(mBinding) {
            tvOne.setOnClickListener { appendOnExpression("1", true) }
            tvTwo.setOnClickListener { appendOnExpression("2", true) }
            tvThree.setOnClickListener { appendOnExpression("3", true) }
            tvFour.setOnClickListener { appendOnExpression("4", true) }
            tvFive.setOnClickListener { appendOnExpression("5", true) }
            tvSix.setOnClickListener { appendOnExpression("6", true) }
            tvSeven.setOnClickListener { appendOnExpression("7", true) }
            tvEight.setOnClickListener { appendOnExpression("8", true) }
            tvNine.setOnClickListener { appendOnExpression("9", true) }
            tvZero.setOnClickListener { appendOnExpression("0", true) }
            tvDot.setOnClickListener { appendOnExpression(".", true) }
            tvPii.setOnClickListener { appendOnExpression("3.1416", true) }

            tvPlus.setOnClickListener { appendOnExpression("+", false) }
            tvMin.setOnClickListener { appendOnExpression("-", false) }
            tvMult.setOnClickListener { appendOnExpression("*", false) }
            tvOpen.setOnClickListener { appendOnExpression("(", false) }
            tvClose.setOnClickListener { appendOnExpression(")", false) }
            tvDivide.setOnClickListener { appendOnExpression("/", false) }


        }
    }

    private fun appendOnExpression(string: String, canClear: Boolean) {

        if (mBinding.tvResult.text.isNotEmpty()) {
            mainViewModel.setFontSizeValueResul(40)
            mainViewModel.setFontSizeValueExpre(50)
            mBinding.tvExpression.text = ""
        }

        if (canClear) {
            mBinding.tvResult.text = ""
            mBinding.tvExpression.append(string)
        } else {
            mBinding.tvExpression.append(mBinding.tvResult.text)
            mBinding.tvExpression.append(string)
            mBinding.tvResult.text = ""
        }
    }

    private fun listEvents() {
        mBinding.swDarkMode.setOnCheckedChangeListener { _, isChecked -> utlils.darkMode(isChecked) }
        mBinding.showSheetBottom.setOnClickListener { showHistories() }
        mBinding.tvClear.setOnClickListener { mainViewModel.clearValue() }
        mBinding.tvBack.setOnClickListener { mainViewModel.backValue(mBinding.tvExpression.text.toString()) }
        mBinding.tvEqual.setOnClickListener { mainViewModel.calculateExpression(mBinding.tvExpression.text.toString()) }

    }

    override fun onClick(operation: OperationEntity) {}


}
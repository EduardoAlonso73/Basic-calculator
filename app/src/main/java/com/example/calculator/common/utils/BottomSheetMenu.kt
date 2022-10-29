package com.example.calculator.common.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.common.entities.OperationEntity
import com.example.calculator.mainModul.viewModel.adapter.OnClickListenerAct
import com.example.calculator.mainModul.viewModel.adapter.OperationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenu(context: Context, private val event: OnClickListenerAct,operationList:MutableList<OperationEntity>) {
    private val bottomSheetDialog by lazy {  BottomSheetDialog(context)}
    private  val mAdapter by lazy {OperationAdapter(event)}
    init {
        mAdapter.submitList(operationList)
        val view= LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog,null)
        bottomSheetDialog.setCancelable(false)
        val btnClose = view.findViewById<ImageButton>(R.id.idBtnDismiss)
        btnClose.setOnClickListener { bottomSheetDialog.dismiss() }
        bottomSheetDialog.setContentView(view)

        with(view){
            findViewById<RecyclerView>(R.id.recyclerView).apply {
                adapter =mAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)

            }
        }


    }

    fun show(){
        bottomSheetDialog.show()
    }
}
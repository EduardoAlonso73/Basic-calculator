package com.example.calculator.mainModul.viewModel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.common.entities.OperationEntity
import com.example.calculator.databinding.ItemOperationBinding

class OperationAdapter(private var listener: OnClickListenerAct) :
    ListAdapter<OperationEntity, RecyclerView.ViewHolder>(OperationDiffCallback()) {
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_operation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val operation = getItem(position)
        with(holder as ViewHolder) {
            mBinding.tvOperation.text=operation.operationExpr
            mBinding.tvResult.text= "= ${operation.resultOperation}"
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mBinding = ItemOperationBinding.bind(view)
        fun setListener(operation: OperationEntity) {
            with(mBinding.root) {
                setOnClickListener { listener.onClick(operation) }
            }
        }
    }


}


class OperationDiffCallback : DiffUtil.ItemCallback<OperationEntity>() {
    override fun areItemsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: OperationEntity, newItem: OperationEntity): Boolean =
        oldItem == newItem

}
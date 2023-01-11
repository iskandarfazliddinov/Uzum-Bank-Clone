package com.example.myapelsin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapelsin.Models.HistoryModel
import com.example.myapelsin.R
import com.example.myapelsin.databinding.HistoryItemsBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class HistoryAdapter(val context: Context,val list: ArrayList<HistoryModel>):RecyclerView.Adapter<HistoryAdapter.VH>() {

    inner class VH(val historyItemsBinding: HistoryItemsBinding):RecyclerView.ViewHolder(historyItemsBinding.root){
        fun onBind(data:HistoryModel){
            historyItemsBinding.apply {
                val dec = DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))

                if(data.history_icon == R.drawable.ucell){
                    historyIcon.scaleType = ImageView.ScaleType.CENTER_CROP
                }else{
                    historyIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
                }
                historyIcon.setImageResource(data.history_icon)
                historyDatas.text = data.history_datas
                if(data.history_money>0){
                    historyMoney.setTextColor(ContextCompat.getColor(context,R.color.first))
                    historyMoney.text = "+${ dec.format(data.history_money).replace(" ", " ") }"
                }else{
                    historyMoney.setTextColor(ContextCompat.getColor(context,R.color.black))
                    historyMoney.text = "${ dec.format(data.history_money).replace(" ", " ") }"
                }
                historyTitle.text = data.history_title
                historyName.text = data.history_name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(HistoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
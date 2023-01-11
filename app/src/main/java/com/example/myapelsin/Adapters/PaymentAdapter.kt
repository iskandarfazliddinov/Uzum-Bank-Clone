package com.example.myapelsin.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapelsin.Models.PaymentModel
import com.example.myapelsin.databinding.PaymentPlacesItemsBinding

class PaymentAdapter(val list: ArrayList<PaymentModel>):RecyclerView.Adapter<PaymentAdapter.VH>() {

    inner class VH(val paymentItems:PaymentPlacesItemsBinding):RecyclerView.ViewHolder(paymentItems.root){
        fun onBind(data:PaymentModel){
            paymentItems.apply {
                paymentIcon.setImageResource(data.Payment_Icon)
                paymentTitle.text = data.Payment_Title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(PaymentPlacesItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
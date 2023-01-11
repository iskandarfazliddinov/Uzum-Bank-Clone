package com.example.myapelsin.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapelsin.Models.AccessModel
import com.example.myapelsin.databinding.QuickAccessItemsBinding

class AccessAdapter(val list: ArrayList<AccessModel>):RecyclerView.Adapter<AccessAdapter.VH>() {

    inner class VH(val quickAccessItemsBinding: QuickAccessItemsBinding):RecyclerView.ViewHolder(quickAccessItemsBinding.root){
        fun onBind(data: AccessModel){
            quickAccessItemsBinding.apply {
                imageIcon.setImageResource(data.image_icon)
                imageTitle.text = data.image_title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(QuickAccessItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
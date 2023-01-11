package com.example.myapelsin.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapelsin.Models.SelectModel
import com.example.myapelsin.databinding.SelectItemsBinding

class SelectAdapter(val list: ArrayList<SelectModel>):RecyclerView.Adapter<SelectAdapter.VH>() {

    inner class VH(val selectItemsBinding: SelectItemsBinding):RecyclerView.ViewHolder(selectItemsBinding.root){
        fun onBind(data: SelectModel){
            selectItemsBinding.apply {
                icons.setImageResource(data.icons)
                title.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(SelectItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
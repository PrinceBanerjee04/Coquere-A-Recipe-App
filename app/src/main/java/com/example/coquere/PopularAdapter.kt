package com.example.coquere

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coquere.databinding.PopularRvItemBinding

class PopularAdapter(
    var dataList: List<Recipe>,
    var context:Context
):RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

    inner class ViewHolder(var binding: PopularRvItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=PopularRvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text= dataList.get(position).tittle
        var time=dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.popularTime.text=time.get(0)
    }
}
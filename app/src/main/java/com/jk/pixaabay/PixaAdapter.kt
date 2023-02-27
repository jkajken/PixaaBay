package com.jk.pixaabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.jk.pixaabay.databinding.ItemPixaBinding
import com.jk.pixaabay.model.Hits

class PixaAdapter(var list: ArrayList<Hits>) : Adapter<PixaAdapter.PixaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemPixaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun addImage(image: ArrayList<Hits>) {
        list.addAll(image)
        notifyItemRangeChanged(3,itemCount)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PixaViewHolder(var binding: ItemPixaBinding) : ViewHolder(binding.root) {
        fun onBind(hits: Hits) {
            binding.pixaImgV.load(hits.largeImageURL)
        }
    }
}
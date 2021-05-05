package com.kodex.kaliningradguide.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kodex.kaliningradguide.R

class MyAdapter(private val onClickTitleCard: OnClickTitleCard) : ListAdapter<Model, MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_title, parent, false), onClickTitleCard
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        holder.itemView.setOnClickListener {
            onClickTitleCard.onClick(model)
        }
    }
}
class DiffCallback : DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem == newItem
    }
}
class MyViewHolder(itemView: View, private val onClickTitleCard: OnClickTitleCard) : RecyclerView.ViewHolder(itemView) {

    private val imageTitle: ImageView = itemView.findViewById(R.id.iv_image)

    fun bind(model: Model) {

        imageTitle.load(model.image)

        imageTitle.setOnClickListener {
            onClickTitleCard.onClick(model)
            Toast.makeText(itemView.context,"Работает Клик",Toast.LENGTH_LONG).show()
        }
    }
}
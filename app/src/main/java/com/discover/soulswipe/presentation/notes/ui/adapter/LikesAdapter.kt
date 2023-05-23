package com.discover.soulswipe.presentation.notes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.discover.soulswipe.R
import com.discover.soulswipe.core.utils.loadImage
import com.discover.soulswipe.core.utils.loadImageWithBlur
import com.discover.soulswipe.data.model.response.notes.ProfileX
import com.discover.soulswipe.databinding.ItemReceivedLikesBinding

class LikesAdapter(private val itemList: List<ProfileX>,private val canSeeProfile:Boolean) : RecyclerView.Adapter<LikesAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(ItemReceivedLikesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class GridViewHolder(private val binding: ItemReceivedLikesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profileResult: ProfileX) = binding.apply {
            tvProfileName.text = profileResult.first_name
            if (canSeeProfile){
                ivProfilePic.loadImage(profileResult.avatar)
            }else{
                ivProfilePic.loadImageWithBlur(profileResult.avatar)
            }
        }
    }
}
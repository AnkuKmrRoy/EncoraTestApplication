package com.encora.encoratestapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.encora.encoratestapplication.R
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity
import com.encora.encoratestapplication.databinding.SongRowItemBinding

class SongListAdapter(private val context: Context, private val songList: List<EncoraSongsEntity>, private val imageClickedListener: onSongClickedListener) : RecyclerView.Adapter<ImageListRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageListRecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SongRowItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.song_row_item, parent, false)
        return ImageListRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ImageListRecyclerViewHolder, position: Int) {
        holder.bind(context,songList[position], imageClickedListener)
    }


}

class ImageListRecyclerViewHolder(val binding: SongRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, songsListItem: EncoraSongsEntity, clickListener: onSongClickedListener?) {

        Glide.with(context).load(songsListItem.icon_url!!).into(binding.songIcon)
        binding.tvSongTitle.setText(songsListItem.title)
        binding.clSongListItem.setOnClickListener {
            clickListener!!.songListItemClicked(songsListItem)
        }
    }

}


interface onSongClickedListener {
    fun songListItemClicked(songData: EncoraSongsEntity)
}



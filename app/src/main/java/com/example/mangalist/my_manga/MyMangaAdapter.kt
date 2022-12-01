package com.example.mangalist.my_manga

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.model.MyManga
import com.example.mangalist.model.WatchStatus

class MyMangaAdapter(
    val mangaList: MutableList<MyManga>
) : RecyclerView.Adapter<MyMangaAdapter.MyMangaViewHolder>() {

    inner class MyMangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iv_my_manga_poster)
        private val title: TextView = itemView.findViewById(R.id.tv_my_manga_list_name)
        private val rating: TextView = itemView.findViewById(R.id.tv_my_manga_list_rating)
        private val rank: TextView = itemView.findViewById(R.id.tv_my_manga_list_status)

        private val bundle = Bundle()

        fun bind(item: MyManga, position: Int) {

            Glide.with(itemView)
                .load(item.images)
                .placeholder(R.drawable.ic_baseline_home_24)
                .into(image)
            title.text = item.title

            if (item.userRating.equals("null")) {
                rating.text = "You didnt score it:("
            } else {
                rating.text = "User rating:" + item.userRating
            }

            rank.text = "Global rank:" + item.rank
            try {
                itemView.setOnClickListener {

                    bundle.putInt("ID", item.id)

                    Navigation.findNavController(itemView)
                        .navigate(R.id.action_myMangaFragment_to_mangaItemFragment, bundle)
                }
            }
            catch (e:Exception)
            {

            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyMangaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_my_manga, parent, false)
        return MyMangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyMangaViewHolder, position: Int) {
        holder.bind(mangaList[position], position)
    }

    override fun getItemCount(): Int = mangaList.size

    fun getItemInPosition(position: Int): MyManga {
        val itemToRemove = mangaList[position]
        mangaList.removeAt(position)
        return itemToRemove
    }

}
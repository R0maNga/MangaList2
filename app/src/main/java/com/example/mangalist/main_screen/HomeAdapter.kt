package com.example.mangalist.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.model.Manga

class HomeAdapter(
    private val mangaList: List<Manga>
) : RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_manga, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mangaList[position])
    }

    override fun getItemCount(): Int = mangaList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.iv_Poster)
        private val title: TextView = itemView.findViewById(R.id.tv_Title)
        private val rating: TextView = itemView.findViewById(R.id.tv_Rating)
        private val bundle = Bundle()

        fun bind(item: Manga) {

            Glide.with(itemView)
                .load(item.images?.jpg?.imageUrl)
                .placeholder(R.drawable.ic_baseline_home_24)
                .into(image)
            rating.text = "${item.score} ★"
            title.text = item.title

                try {
                    itemView.setOnClickListener {
                        bundle.putInt("ID", item.id)
                        Navigation.findNavController(itemView)
                            .navigate(R.id.action_homeFragment_to_mangaItemFragment, bundle)
                    }

                }
                catch (e:Exception)
                {

                }

        }
    }
}
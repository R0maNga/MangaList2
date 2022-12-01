package com.example.mangalist.read_manga_item

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
import com.example.mangalist.model.Chapter
import com.example.mangalist.model.MyManga
import com.example.mangalist.my_manga.MyMangaAdapter


class ReadMangaItemAdapter(
    val chapterList: MutableList<Chapter>
    ) : RecyclerView.Adapter<ReadMangaItemAdapter.MyChapterViewHolder>() {


    inner class MyChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val chapter: TextView = itemView.findViewById(R.id.rv_chapter_name)

        private val bundle = Bundle()
        fun bind(item: Chapter, position: Int) {


            chapter.text = item.chapterName



            try {
                itemView.setOnClickListener {

                    bundle.putSerializable("Chapter",item)

                    Navigation.findNavController(itemView)
                        .navigate(R.id.action_readMangaItemFragment3_to_mangaPannelFragment, bundle)
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
    ): MyChapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_read_manga, parent, false)
        return MyChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyChapterViewHolder, position: Int) {
        holder.bind(chapterList[position], position)

    }

    override fun getItemCount(): Int = chapterList.size
}

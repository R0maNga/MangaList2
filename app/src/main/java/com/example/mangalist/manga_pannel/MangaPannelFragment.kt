package com.example.mangalist.manga_pannel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.databinding.MangaPannelFragmentBinding
import com.example.mangalist.model.Chapter


class MangaPannelFragment() : Fragment() {

    private lateinit var binding: MangaPannelFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MangaPannelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var chapterNumber = 1
      val chapter = arguments?.getSerializable("Chapter") as Chapter

        Glide.with(binding.mangaPage)
            .load("https://v2.yuucdn.net/wp-content/uploads/C/Chainsawman/Chapter%200${chapter.id}/00${chapterNumber}.jpg")

            .into(binding.mangaPage)

        binding.next.setOnClickListener(){
            chapterNumber +=1
            if(chapterNumber < 10){
                try {

                    Glide.with(binding.mangaPage)
                        .load("https://v2.yuucdn.net/wp-content/uploads/C/Chainsawman/Chapter%200${chapter.id}/00${chapterNumber}.jpg")

                        .into(binding.mangaPage)
                }
                catch (e:Exception)
                {
                    e.printStackTrace()
                }
            }else{
                try {
                    Glide.with(binding.mangaPage)
                        .load("https://v2.yuucdn.net/wp-content/uploads/C/Chainsawman/Chapter%200${chapter.id}/0${chapterNumber}.jpg")
                        .error(R.drawable.no_image_available)
                        .into(binding.mangaPage)
                }
                catch (e:Exception)
                {

                }
            }

        }


        binding.back.setOnClickListener(){

            if (    chapterNumber == 1)
            {
                Toast.makeText(context, "First Page", Toast.LENGTH_SHORT).show()
            }
            else{
                chapterNumber -=1
            }
            if(chapterNumber < 10) {
                try {
                    Glide.with(binding.mangaPage)
                        .load("https://v2.yuucdn.net/wp-content/uploads/C/Chainsawman/Chapter%200${chapter.id}/00${chapterNumber}.jpg")
                        .into(binding.mangaPage)
                } catch (e: Exception) {

                }
            }else{

                try {
                    Glide.with(binding.mangaPage)
                        .load("https://v2.yuucdn.net/wp-content/uploads/C/Chainsawman/Chapter%200${chapter.id}/0${chapterNumber}.jpg")
                        .error(R.drawable.no_image_available)
                        .into(binding.mangaPage)
                } catch (e: Exception) {

                }
            }


        }
    }




}
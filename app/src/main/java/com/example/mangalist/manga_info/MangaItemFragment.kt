package com.example.mangalist.manga_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentMangaItemBinding
import com.example.mangalist.model.DbManga
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MangaItemFragment : Fragment() {
    private val mangaItemViewModel = MangaItemViewModel()
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var binding: FragmentMangaItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        val id = arguments?.getInt("ID")
        mangaItemViewModel.getMangaById(id!!)
        mangaItemViewModel.liveMangaData.observe(viewLifecycleOwner) { mangaList ->
            try {
                Glide.with(view)
                    .load(mangaList.data.images?.jpg?.imageUrl)
                    .placeholder(R.drawable.ic_baseline_home_24)
                    .into(binding.ivPoster)
                binding.tvTitle.text = mangaList.data.title
                binding.tvDescription.text = mangaList.data.synopsis

                binding.btnAddManga.setOnClickListener {
                    if (auth.currentUser != null) {

                        database.child(
                            auth.currentUser?.email.toString().substringBefore("@")
                        )
                            .child(mangaList.data.id.toString()).setValue(
                                DbManga(
                                    mangaList.data.id.toString(),
                                    mangaList.data.images!!.jpg.imageUrl,
                                    mangaList.data.title,
                                    mangaList.data.rank.toString(),
                                   // binding.spinner.selectedItemPosition.toString()
                                )
                            ).addOnSuccessListener {
                                Toast.makeText(context, "dsf", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {
                                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()

                            }

                    } else {
                        Toast.makeText(context, "Войдите в аккаунт!", Toast.LENGTH_SHORT).show()
                    }

                }

            } catch (e: Exception) {
                Log.e("+", e.message.toString())
            }
        }



    }
}
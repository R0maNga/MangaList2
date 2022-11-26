package com.example.mangalist.manga_info

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.databinding.FragmentMangaItemBinding
import com.example.mangalist.model.DbManga
import com.example.mangalist.model.WatchStatus
import com.example.mangalist.retrofit.TestRetrofit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

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





        val status = resources.getStringArray(R.array.spinner_array)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.spinnerlayout, status)
        binding.spinner.adapter = arrayAdapter

        super.onCreate(savedInstanceState)
        val text = arguments?.getString("Rating")


        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        val id = arguments?.getInt("ID")
        mangaItemViewModel.getMangaById(id!!)
        mangaItemViewModel.processingData(id.toString())

        mangaItemViewModel.liveMangaDataStatus.observe(viewLifecycleOwner) {
            if (it) {
                database.child(auth.currentUser?.email.toString().substringBefore("@"))
                    .child(id.toString()).child("watchStatus").get().addOnSuccessListener {
                        it.value
                        binding.spinner.setSelection(it.value.toString().toInt())
                        val watchStatus = it.value
                        binding.spinner.onItemSelectedListener =
                            object : OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {

                                }

                                override fun onNothingSelected(p0: AdapterView<*>?) {

                                }

                            }

                    }
            }

        }
        mangaItemViewModel.liveMangaData.observe(viewLifecycleOwner) { mangaList ->
            try {

                binding.rateMangaTextView.setOnClickListener {
                    var dialog = CustomDialogFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("ID", DbManga(mangaList.data.id.toString(),
                        mangaList.data.images?.jpg?.imageUrl.toString(),
                        mangaList.data.title.toString(),
                        mangaList.data.rank.toString(),
                        binding.spinner.selectedItemPosition.toString(),
                        ""))
                    dialog.arguments = bundle
                    dialog.show(childFragmentManager, "customDialog")
                }

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
                                    binding.spinner.selectedItemPosition.toString(),
                                    text.toString()
                                    /*tv.text.toString()*/

                                )
                            ).addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    "Successfully added",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }.addOnFailureListener {
                                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT)
                                    .show()

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

package com.example.mangalist.manga_info

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentCustomDialogBinding
import com.example.mangalist.model.DbManga
import com.example.mangalist.model.Test
import com.example.mangalist.model.UserRating
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CustomDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCustomDialogBinding
    private val mangaItemViewModel = MangaItemViewModel()
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var bundle = Bundle()

    //var dialogFragmentArray :MutableList<String> = emptyList<String>().toMutableList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val manga = arguments?.getSerializable("ID") as DbManga

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.submitButton.setOnClickListener {

            val selectedId = binding.ratingRadioGroup.checkedRadioButtonId
            val awe = binding.getRoot().findViewById<RadioButton>(selectedId)
            val amog = awe.text.toString()


            if (auth.currentUser != null) {

                database.child(
                    auth.currentUser?.email.toString().substringBefore("@")
                )
                    .child(manga.id.toString()).setValue(
                        DbManga(
                            manga.id,
                            manga.images,
                            manga.title,
                            manga.rank,
                            manga.watchStatus,
                            amog
                        )
                    )
                    .addOnSuccessListener {
//                        Toast.makeText(
//                            requireContext(),
//                            "Successfully added",
//                            Toast.LENGTH_SHORT
//                        ).show()

                    }.addOnFailureListener {
//                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT)
//                            .show()

                    }

            } else {
//                Toast.makeText(context, "Войдите в аккаунт!", Toast.LENGTH_SHORT).show()
            }


            bundle.putString("Rating", amog)
//            Toast.makeText(requireContext(), "$amog", Toast.LENGTH_LONG).show()
            dismiss()
        }

    }
}

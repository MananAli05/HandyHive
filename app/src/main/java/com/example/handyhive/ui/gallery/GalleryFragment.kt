package com.example.handyhive.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
// import androidx.lifecycle.ViewModelProvider // Commented ViewModel import
import com.example.handyhive.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ViewModel setup code is commented out
        // val galleryViewModel =
        //     ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textGallery

        // Commented out LiveData observation from ViewModel
        // galleryViewModel.text.observe(viewLifecycleOwner) {
        //     textView.text = it // Sets the default text dynamically
        // }

        // Setting a static default text directly instead of using ViewModel
        //textView.text = "This is gallery Fragment"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

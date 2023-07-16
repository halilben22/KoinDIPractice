package com.example.koinlearn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinlearn.databinding.FragmentListBinding
import com.example.koinlearn.model.CryptoModel
import com.example.koinlearn.service.CryptoAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(), RecyclerViewAdapter.Listener {
   private var _binding: FragmentListBinding? = null

   // This property is only valid between onCreateView and
// onDestroyView.
   private val binding get() = _binding!!




   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {

      _binding = FragmentListBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val layoutManager = LinearLayoutManager(requireContext())
      binding.recyclerView.layoutManager = layoutManager


   }



   override fun onItemClick(cryptoModel: CryptoModel) {
      Toast.makeText(requireContext(), "Clicked on:${cryptoModel.currency}", Toast.LENGTH_SHORT)
         .show()
   }

}


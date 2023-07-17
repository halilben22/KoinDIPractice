package com.example.koinlearn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinlearn.databinding.FragmentListBinding
import com.example.koinlearn.model.CryptoModel
import com.example.koinlearn.viewmodel.CryptoViewModel


class ListFragment : Fragment(), RecyclerViewAdapter.Listener {
   private var _binding: FragmentListBinding? = null
   private val binding get() = _binding!!
   private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(), this)

   val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[CryptoViewModel::class.java]
   }


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
      viewModel.getDataFromApi()

   }

   private fun observeLiveData() {

      viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptos ->
         cryptos.let {
            cryptoAdapter = RecyclerViewAdapter(ArrayList(cryptos), this)
            binding.recyclerView.adapter = cryptoAdapter
         }
      })



   }


   override fun onItemClick(cryptoModel: CryptoModel) {
      Toast.makeText(requireContext(), "Clicked on:${cryptoModel.currency}", Toast.LENGTH_SHORT)
         .show()
   }

}


package com.example.koinlearn.viewmodel

import androidx.lifecycle.ViewModel
import com.example.koinlearn.model.CryptoModel
import com.example.koinlearn.service.CryptoAPI
import com.example.koinlearn.utils.Constants.BASE_URL
import com.example.koinlearn.view.RecyclerViewAdapter
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel(){
   private var cryptoModels: ArrayList<CryptoModel>? = null
   private var job: Job? = null
   fun getDataFromApi(){
      val retrofit =
         Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(CryptoAPI::class.java)


      job = CoroutineScope(Dispatchers.IO).launch {
         val response = retrofit.getCryptoList()
         withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
               response.body()?.let {
              
               }
            }


         }
      }
   }
}
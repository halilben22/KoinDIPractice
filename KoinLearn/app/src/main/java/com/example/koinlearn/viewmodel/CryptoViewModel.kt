package com.example.koinlearn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koinlearn.model.CryptoModel
import com.example.koinlearn.service.CryptoAPI
import com.example.koinlearn.utils.Constants.BASE_URL
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {
   private var cryptoModels: ArrayList<CryptoModel>? = null
   private var job: Job? = null
   val cryptoList = MutableLiveData<List<CryptoModel>>()

   val cryptoError = MutableLiveData<Boolean>()

   val cryptoLoading = MutableLiveData<Boolean>()


   fun getDataFromApi() {
      val retrofit =
         Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(CryptoAPI::class.java)


      job = CoroutineScope(Dispatchers.IO).launch {
         val response = retrofit.getCryptoList()
         withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
               cryptoError.value = false
               cryptoLoading.value = false
               response.body()?.let {
                  cryptoList.value=it
               }
            }


         }
      }

   }

   override fun onCleared() {
      super.onCleared()
      job!!.cancel()

   }
}
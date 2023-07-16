package com.example.koinlearn.service

import com.example.koinlearn.model.CryptoModel
import okhttp3.Response
import retrofit2.http.GET

interface CryptoAPI {

   @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
   fun getCryptoList():retrofit2.Response<List<CryptoModel>>




}
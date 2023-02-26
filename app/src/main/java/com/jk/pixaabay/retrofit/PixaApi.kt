package com.jk.pixaabay.retrofit

import com.jk.pixaabay.model.PixaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("key") key: String = "32430227-a1b9aa4d617ba460e51a3b8b4",
        @Query("q") searchWord: String,
        @Query("page") page: Int,
        @Query("per_page") per_page:Int = 3
    ): Call<PixaModel>
}


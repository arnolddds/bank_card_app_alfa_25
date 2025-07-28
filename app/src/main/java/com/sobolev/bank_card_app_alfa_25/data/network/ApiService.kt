package com.sobolev.bank_card_app_alfa_25.data.network

import com.sobolev.bank_card_app_alfa_25.data.network.dto.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinInfo(
        @Path("bin") bin: String
    ): BinInfoDto
}
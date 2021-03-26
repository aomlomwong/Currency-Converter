package th.ac.su.ict.currencyconverter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import th.ac.su.ict.currencyconverter.data.RateResponse

interface RateSevice {

    @GET("latest?base=USD")
    fun getCurrentRateData(@Query("base")unit:String) :Call<RateResponse>


}
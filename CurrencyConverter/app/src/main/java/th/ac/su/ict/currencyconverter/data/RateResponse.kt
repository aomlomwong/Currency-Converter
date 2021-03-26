package th.ac.su.ict.currencyconverter.data

import com.google.gson.annotations.SerializedName

   
data class RateResponse (

   @SerializedName("rates") var rates : Rates,
   @SerializedName("base") var base : String,
   @SerializedName("date") var date : String

)
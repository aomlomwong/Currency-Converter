package th.ac.su.ict.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.ac.su.ict.currencyconverter.data.RateResponse

class MainActivity : AppCompatActivity() {

    lateinit var edtInput: EditText
    lateinit var tvRate:TextView
    lateinit var tvUSD: TextView
    lateinit var tvTHB: TextView

    var BASE_URL = "https://api.exchangeratesapi.io/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtInput = findViewById<EditText>(R.id.edtInput)
        tvRate = findViewById<TextView>(R.id.tvRate)
        tvTHB = findViewById<TextView>(R.id.tvTHB)
        tvUSD = findViewById<TextView>(R.id.tvUSD)





        val btnOK = findViewById<Button>(R.id.btnOK)

        btnOK.setOnClickListener {

            getCurrentRate()


        }

    }

    fun getCurrentRate(){
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RateSevice::class.java)
        val call = service.getCurrentRateData("USD")
        call.enqueue(object: Callback<RateResponse>{
            override fun onFailure(call: Call<RateResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onResponse
                    (call: Call<RateResponse>?,
                     response: Response<RateResponse>?) {

               if(response!=null){
                   if(response.code() == 200){

                       val rateResponse = response.body()
                       tvRate.text = "Rate Today = "+rateResponse.rates.THB.toString()
                       var usd:Float =  edtInput.text.toString().toFloat()
                       var rateUSD:Float =  rateResponse.rates.THB.toFloat()
                       var thb = usd * rateUSD
                       tvTHB.setText(thb.toString()+" THB")
                       tvUSD.setText(usd.toString()+" USD")




                   }
               }
            }
        })
    }
}




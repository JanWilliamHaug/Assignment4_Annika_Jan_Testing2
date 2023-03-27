package com.example.assignment4_annika_jan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.assignment4_annika_jan.API.APIInterface
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIActivity : AppCompatActivity() {

    val db = Firebase.firestore

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apiactivity)

        val doAPICall = findViewById<Button>(R.id.api_call_btn)
        doAPICall.setOnClickListener {
            getJsondata()
        }
    }

    private fun getJsondata() {
        val jsondata = retrofitBuilder.getAPIData()
        jsondata.enqueue(object : Callback<List<DataAPIItem>?> {
            override fun onResponse(
                call: Call<List<DataAPIItem>?>,
                response: Response<List<DataAPIItem>?>
            ) {
                val responseReturn = response.body()!!
                for (data in responseReturn) {
                    val jsonData = DataAPIItem(data.body, data.id, data.title, data.userId)
                    db.collection("JSONData")
                    .add(jsonData)
                }
            }

            override fun onFailure(call: Call<List<DataAPIItem>?>, t: Throwable) {
                Log.d("APIFAIL", "message" + t.message)
            }
        })
    }


}
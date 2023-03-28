package com.example.assignment4_annika_jan

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        val db = Firebase.firestore
        val usersRef = db.collection("JSONData")

        val userList = mutableListOf<DataAPIItem>()
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        usersRef.get().addOnSuccessListener { result ->
            userList.clear()
            for (document in result) {
                val user = document.toObject(DataAPIItem::class.java)
                userList.add(user)
            }
            recyclerView.adapter?.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error retrieving data", exception)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(userList)

    }
}
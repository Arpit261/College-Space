package com.arpit.collegeuser.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Adaptors.guesspaperAdaptor
import com.arpit.collegeuser.Model.guesspaper
import com.arpit.collegeuser.R
import com.google.firebase.firestore.FirebaseFirestore

class viewGuessPaper : AppCompatActivity() {

    private lateinit var firestoreRef: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: guesspaperAdaptor

    private var fakeData = arrayListOf<guesspaper>()

    private var getBranchForGuesspapers=""

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_guesssspaper)

        getBranchForGuesspapers = intent.getStringExtra("GuessBranch").toString()
        firestoreRef = FirebaseFirestore.getInstance()

        recyclerView  = findViewById(R.id.recyclerGuessPaper)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptor = guesspaperAdaptor(this,fakeData)
        recyclerView.adapter = adaptor

        getGuessPapersFromFireStore()

    }
    private fun getGuessPapersFromFireStore(){

        db.collection("GuessPapers").document(getBranchForGuesspapers).collection("Docs")
                .get()
                .addOnSuccessListener {

                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {

                            val z: guesspaper? = d.toObject(guesspaper::class.java)

                            Log.d("Data2", "success ${d.data}")
                            if (z != null) {
                                fakeData.add(z)
                                Log.d("added","DataAdded $fakeData")
                            }
                        }

                        adaptor.notifyDataSetChanged()

                    } else {
                        Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
                    }


                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }




    }
}
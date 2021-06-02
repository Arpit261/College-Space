package com.arpit.collegeuser.Activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Adaptors.AssignmentAdaptor

import com.arpit.collegeuser.Model.AssignmentModel
import com.arpit.collegeuser.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ViewAssignmentActivity : AppCompatActivity() {

    private lateinit var firestoreRef: FirebaseFirestore
    private lateinit var recyclerView1: RecyclerView
    private lateinit var adaptor1: AssignmentAdaptor

    private var imageList = arrayListOf<AssignmentModel>()


    private var getBranch=""
    private var getSem=""
    private var getUnit=""
   lateinit var  downloadUri:Uri

    //private var getBranchForGuesspapers=""
 //   private var getSemForGuesspapers=""
  //  private var getUnitForGuesspapers=""

    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notes)

        getBranch = intent.getStringExtra("category").toString()
        getSem = intent.getStringExtra("sem").toString()
        getUnit = intent.getStringExtra("unit").toString()


       // getBranchForGuesspapers = intent.getStringExtra("GuessBranch").toString()
     //   getSemForGuesspapers = intent.getStringExtra("GuessSem").toString()
       // getUnitForGuesspapers = intent.getStringExtra("GuessUnit").toString()



        firestoreRef = FirebaseFirestore.getInstance()

        recyclerView1 = findViewById(R.id.reyclerviewAssignment)


        recyclerView1.layoutManager = LinearLayoutManager(this)
        adaptor1 = AssignmentAdaptor(this, imageList)
        recyclerView1.adapter = adaptor1


        getAssignmentFromFireStore()




    }

    private fun getAssignmentFromFireStore() {

        db.collection("UsersAssignment")
                .document(getBranch)
                .collection("Branch")
                .document(getSem)
                .collection("Semester")
                .document(getUnit)
                .collection("Units")


                .orderBy("imagesUrl", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {

                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {

                            Log.d("Data", "success")
                            val c: AssignmentModel? = d.toObject(AssignmentModel::class.java)

                            if (c != null) {
                                imageList.add(c)
                            }
                        }


                        adaptor1.notifyDataSetChanged()

                    } else {
                        Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
                    }


                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }


    }


}




package com.arpit.collegeuser.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Adaptors.ClassNotesAdaptor
import com.arpit.collegeuser.Model.classNotes
import com.arpit.collegeuser.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_view_class_notes.*

class ViewClassNotes : AppCompatActivity() {

    private lateinit var firestoreRef: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: ClassNotesAdaptor
    private var getBranchForClassNotes = ""

    private var classData = arrayListOf<classNotes>()

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_class_notes)

        getBranchForClassNotes = intent.getStringExtra("NotesBranch").toString()
        firestoreRef = FirebaseFirestore.getInstance()



        recyclerView = findViewById(R.id.recyclerNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptor = ClassNotesAdaptor(this, classData)
        recyclerView.adapter = adaptor


        getNotesFromFirestore()

    }


    private fun getNotesFromFirestore() {

        db.collection("Pdf").document(getBranchForClassNotes).collection("PDFS")
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {

                            val x: classNotes? = d.toObject(classNotes::class.java)
                            if (x != null) {
                                classData.add(x)
                            }
                            adaptor.notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(this, "NO data found", Toast.LENGTH_SHORT).show()
                    }


                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }
    }
}
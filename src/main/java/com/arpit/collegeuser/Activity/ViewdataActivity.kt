package com.arpit.collegeuser.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Adaptors.AssignmentAdaptor
import com.arpit.collegeuser.Adaptors.ClassNotesAdaptor
import com.arpit.collegeuser.Adaptors.guesspaperAdaptor
import com.arpit.collegeuser.Model.AssignmentModel
import com.arpit.collegeuser.Model.classNotes
import com.arpit.collegeuser.Model.guesspaper
import com.arpit.collegeuser.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ViewdataActivity : AppCompatActivity() {

    private lateinit var firestoreRef: FirebaseFirestore
    val db = FirebaseFirestore.getInstance()

    private lateinit var recyclerView: RecyclerView
    private lateinit var guessPaperAdaptor: guesspaperAdaptor
    private lateinit var classNotesAdaptor: ClassNotesAdaptor
    private lateinit var assignmentAdaptor: AssignmentAdaptor

    private var guesspaperData = arrayListOf<guesspaper>()
    private var classData = arrayListOf<classNotes>()
    private var assignmentData = arrayListOf<AssignmentModel>()

    private var getBranchforAssignment = ""
    private var getSemforAssignment = ""
    private var getUnitforAssignment = ""

    private var getBranchForGuesspapers = ""
    private var getSemforGuesspaper=""

    private var getBranchForClassNotes = ""
    private var getSemForNotes = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewdata)


        getBranchforAssignment = intent.getStringExtra("category").toString()
        getSemforAssignment = intent.getStringExtra("sem").toString()
        getUnitforAssignment = intent.getStringExtra("unit").toString()

        getBranchForClassNotes = intent.getStringExtra("NotesBranch").toString()
        getSemForNotes = intent.getStringExtra("NotesSem").toString()

        getBranchForGuesspapers = intent.getStringExtra("GuessBranch").toString()
        getSemforGuesspaper=intent.getStringExtra("GuessSem").toString()

        recyclerView = findViewById(R.id.recyclerViewData)

        firestoreRef = FirebaseFirestore.getInstance()


        getAssignmentFromFireStore()
        getNotesFromFirestore()
        getGuessPapersFromFireStore()
    }

    private fun getAssignmentFromFireStore() {

        db.collection("UsersAssignment")
                .document(getBranchforAssignment)
                .collection("Branch")
                .document(getSemforAssignment)
                .collection("Semester")
                .document(getUnitforAssignment)
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
                                assignmentData.add(c)
                            }
                        }

                        recyclerView.layoutManager = LinearLayoutManager(this)
                        assignmentAdaptor = AssignmentAdaptor(this, assignmentData)
                        recyclerView.adapter = assignmentAdaptor
                        assignmentAdaptor.notifyDataSetChanged()
                    }

                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }
    }

    private fun getNotesFromFirestore() {

        db.collection("User Pdf")
                .document(getBranchForClassNotes)
                .collection("Sem")
                .document(getSemForNotes)
                .collection("Data")
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {

                            val x: classNotes? = d.toObject(classNotes::class.java)
                            if (x != null) {
                                classData.add(x)
                            }

                            recyclerView.layoutManager = LinearLayoutManager(this)
                            classNotesAdaptor = ClassNotesAdaptor(this, classData)
                            recyclerView.adapter = classNotesAdaptor
                            classNotesAdaptor.notifyDataSetChanged()
                        }
                    }
                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }
    }

    private fun getGuessPapersFromFireStore() {

        db.collection("GuessPapers")
                .document(getBranchForGuesspapers)
                .collection("Sem")
                .document(getSemforGuesspaper)
                .collection("data")
                .get()
                .addOnSuccessListener {

                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {

                            val z: guesspaper? = d.toObject(guesspaper::class.java)

                            Log.d("Data2", "success ${d.data}")
                            if (z != null) {
                                guesspaperData.add(z)
                                Log.d("added", "DataAdded $guesspaperData")
                            }
                        }

                        recyclerView.layoutManager = LinearLayoutManager(this)
                        guessPaperAdaptor = guesspaperAdaptor(this, guesspaperData)
                        recyclerView.adapter = guessPaperAdaptor
                        guessPaperAdaptor.notifyDataSetChanged()

                    }
                }.addOnFailureListener {

                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }
    }
}

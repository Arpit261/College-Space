package com.arpit.collegeuser.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Adaptors.NoticeAdaptor
import com.arpit.collegeuser.Model.NoticeModel
import com.arpit.collegeuser.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class NoticeFragments : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: NoticeAdaptor
    private var imageList = arrayListOf<NoticeModel>()
    private lateinit var firestoreRef: FirebaseFirestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notice, container, false)

        firestoreRef = FirebaseFirestore.getInstance()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity as Context)
        adaptor = NoticeAdaptor(activity as Context, imageList)
        recyclerView.adapter = adaptor

        getNoticeFromFireStore()

        return view
    }

    private fun getNoticeFromFireStore() {

        db.collection("DepartmentNotice")
                .orderBy("Imageurl", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {

                    if (!it.isEmpty) {
                        val data = it.documents
                        for (d in data) {
                            Log.d("Data", "Success")

                            val c: NoticeModel? = d.toObject(NoticeModel::class.java)
                            if (c != null) {
                                imageList.add(c)
                            }
                        }
                        adaptor.notifyDataSetChanged()

                    } else {
                        Toast.makeText(activity as Context, "No data found", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {

                    Toast.makeText(activity as Context, "Fail to get the data.", Toast.LENGTH_SHORT).show();
                }
    }
}
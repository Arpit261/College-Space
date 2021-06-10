package com.arpit.collegeuser.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.arpit.collegeuser.Activity.ViewdataActivity
import com.arpit.collegeuser.Model.SelectBranch
import com.arpit.collegeuser.Model.SelectSem
import com.arpit.collegeuser.Model.SelectUnit
import com.arpit.collegeuser.R

class NotesFragment : Fragment() {

    var branch = ""
    var sem = ""

    lateinit var button: Button
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner

    private lateinit var arrayAdaptor: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        spinner1 = view.findViewById(R.id.SelectBranchForNotes)
        spinner2 = view.findViewById(R.id.SelectSemesterForNotes)

        button = view.findViewById(R.id.btnViewNotes)
        button.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("NotesBranch", branch)
            bundle.putString("NotesSem", sem)
            val intent = Intent(activity as Context, ViewdataActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        selectBranchNotesCategory()
        selectSemNotesCategory()


        return view
    }

    private fun selectSemNotesCategory() {
        arrayAdaptor = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, SelectSem)
        spinner2.adapter = arrayAdaptor

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                sem = spinner2.selectedItem.toString()
            }
        }
    }

    private fun selectBranchNotesCategory() {

        arrayAdaptor = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, SelectBranch)
        spinner1.adapter = arrayAdaptor

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                branch = spinner1.selectedItem.toString()
            }
        }
    }
}
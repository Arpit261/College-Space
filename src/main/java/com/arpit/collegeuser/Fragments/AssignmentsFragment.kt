package com.arpit.collegeuser.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.arpit.collegeuser.Activity.ViewAssignmentActivity
import com.arpit.collegeuser.Model.*
import com.arpit.collegeuser.R

class AssignmentsFragment : Fragment() {

    lateinit var button: Button
    var branch = ""
    var sem = ""
    var unit = ""
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    lateinit var spinner3: Spinner

    private lateinit var arrayAdaptor: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_assignments, container, false)

        spinner1 = view.findViewById(R.id.SelectBranchForAssignment)
        spinner2 = view.findViewById(R.id.SelectSemesterForAssignment)

        spinner3 = view.findViewById(R.id.SelectUnitForAssignment)




        button = view.findViewById(R.id.btnViewAssignment)

        button.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("category", branch)
            bundle.putString("sem", sem)
            bundle.putString("unit", unit)


            val intent = Intent(activity as Context, ViewAssignmentActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }



        selectBranchAssignmentCategory()
        selectSemAssignmentCategory()
        selectUnitAssignmentCategory()

        return view

    }


    private fun selectBranchAssignmentCategory() {

        arrayAdaptor = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, SelectBranch)
        spinner1.adapter = arrayAdaptor

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                branch = spinner1.selectedItem.toString()


            }

        }

    }

    private fun selectSemAssignmentCategory() {


        arrayAdaptor = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, SelectSem)
        spinner2.adapter = arrayAdaptor

        spinner2.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        sem = spinner2.selectedItem.toString()


                    }

                }

    }

    private fun selectUnitAssignmentCategory() {


        arrayAdaptor = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, SelectUnit)

        spinner3.adapter = arrayAdaptor

        spinner3.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        unit = spinner3.selectedItem.toString()


                    }

                }

    }


}



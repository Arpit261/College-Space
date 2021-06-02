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
import com.arpit.collegeuser.Activity.viewGuessPaper
import com.arpit.collegeuser.Model.SelectBranch
import com.arpit.collegeuser.R

class GuesspaperFragment : Fragment() {

    lateinit var button: Button
    var branch = ""
    var sem = ""
    var unit = ""
    lateinit var spinner1: Spinner
   // lateinit var spinner2: Spinner
    //lateinit var spinner3: Spinner
    private lateinit var arrayAdaptor: ArrayAdapter<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_guesspaper, container, false)



        spinner1 = view.findViewById(R.id.SelectBranchForGuesspaper)
      //  spinner2 = view.findViewById(R.id.SelectSemForGuesspaper)
        //spinner3 = view.findViewById(R.id.SelectUnitForGuesspaper)



        button = view.findViewById(R.id.btnViewGuesspapers)
        button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("GuessBranch", branch)
        //    bundle.putString("GuessSem", sem)
          //  bundle.putString("GuessUnit", unit)
            val intent = Intent(activity as Context, viewGuessPaper::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        selectBranchGuessPapaersCategory()
      //  selectSemGuesspapersCategory()
        //selectUnitGuesspapersCategory()


        return view
    }


    private fun selectBranchGuessPapaersCategory() {

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

  /*  private fun selectSemGuesspapersCategory() {


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

    private fun selectUnitGuesspapersCategory() {


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

   */


}
package com.arpit.collegeuser.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arpit.collegeuser.Fragments.*
import com.arpit.collegeuser.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openHome()

        bottom_menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {

                when (id) {
                    R.id.home -> openHome()
                    R.id.Notes -> openNotes()
                    R.id.notice -> openNotice()
                    R.id.Assignment -> openAssignment()
                    R.id.guessPaper -> openGuesspaper()
                }
            }
        })
    }

    private fun openGuesspaper() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, GuesspaperFragment())
                .commit()
        supportActionBar?.title = "Guesspaper"
    }

    private fun openAssignment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AssignmentsFragment())
                .commit()
        supportActionBar?.title = "Assignment"
    }

    private fun openNotice() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, NoticeFragments())
                .commit()
        supportActionBar?.title = "Notice"
    }

    private fun openNotes() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, NotesFragment())
                .commit()
        supportActionBar?.title = "Notes"
    }

    fun openHome() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, HomeFragment())
                .commit()
        supportActionBar?.title = "Home"
    }
}
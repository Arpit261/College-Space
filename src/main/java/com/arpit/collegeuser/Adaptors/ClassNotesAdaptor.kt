package com.arpit.collegeuser.Adaptors

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Model.classNotes
import com.arpit.collegeuser.R

class ClassNotesAdaptor(val context: Context, val itemList: ArrayList<classNotes>) : RecyclerView.Adapter<ClassNotesAdaptor.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_notes_fragment, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val text = itemList[position]
        holder.title.text = text.pdfTitle

        holder.title.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(itemList[position].PdfUrl)
            context.startActivity(intent)
        }

    }


    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textNotes)


    }


}
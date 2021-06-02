package com.arpit.collegeuser.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Model.AssignmentModel
import com.arpit.collegeuser.R
import com.bumptech.glide.Glide


class AssignmentAdaptor(val context: Context, val itemList: ArrayList<AssignmentModel>) : RecyclerView.Adapter<AssignmentAdaptor.AssignmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_assignment_fragment, parent, false)
        return AssignmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val image = itemList[position]
        Glide.with(holder.itemView.context)
                .load(image.imagesUrl)
                .into(holder.noticeView)

    }


    class AssignmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noticeView: ImageView = view.findViewById(R.id.imageViewAssignment)



    }
}
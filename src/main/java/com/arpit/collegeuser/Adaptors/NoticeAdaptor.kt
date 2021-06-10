package com.arpit.collegeuser.Adaptors

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Activity.Full_Image_view
import com.arpit.collegeuser.Model.NoticeModel
import com.arpit.collegeuser.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.single_notice_fragment.view.*

class NoticeAdaptor(val context: Context, val itemList: ArrayList<NoticeModel>) : RecyclerView.Adapter<NoticeAdaptor.NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_notice_fragment, parent, false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {

        val image = itemList[position]
        holder.date.text = image.date
        holder.time.text = image.time
        holder.branchName.text = image.Branch
        Glide.with(holder.itemView.context)
                .load(image.Imageurl)
                .into(holder.NoticeView)


        holder.NoticeView.setOnClickListener {
            var intent = Intent()
            intent = Intent(context, Full_Image_view::class.java)
            intent.putExtra("full_image", image.Imageurl)
            context.startActivity(intent)
        }
    }

    class NoticeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val NoticeView: ImageView = view.findViewById(R.id.imageViewNotice)
        val date: TextView = view.findViewById(R.id.date)
        val time: TextView = view.findViewById(R.id.time)
        val branchName:TextView = view.findViewById(R.id.branchName)
    }
}
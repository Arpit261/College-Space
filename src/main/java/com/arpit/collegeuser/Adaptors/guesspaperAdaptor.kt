package com.arpit.collegeuser.Adaptors

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Model.guesspaper
import com.arpit.collegeuser.R


class guesspaperAdaptor(val context: Context, private val itemList: ArrayList<guesspaper>) : RecyclerView.Adapter<guesspaperAdaptor.GuesspaperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuesspaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_guesspaper_fragment, parent, false)
        return GuesspaperViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: GuesspaperViewHolder, position: Int) {
        val text = itemList[position]
        holder.title.text = text.docsName
        holder.unit.text= "Unit ${text.DocsUnit}"

        holder.imgShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Checkout this Document ,${itemList[position].DocsUrl}")
            val chooser = Intent.createChooser(intent,"Share this Document to...")
            context.startActivity(chooser)
        }

        holder.imgDownload.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(itemList[position].DocsUrl)
            context.startActivity(intent)
        }
    }

    class GuesspaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.guesspaperTitle)
        val imgDownload: ImageView = view.findViewById(R.id.download_guesspaper)
        val unit:TextView = view.findViewById(R.id.textUnit)
        val imgShare:ImageView=view.findViewById(R.id.shareDocs)
    }
}
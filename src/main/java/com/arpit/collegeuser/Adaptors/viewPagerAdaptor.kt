package com.arpit.collegeuser.Adaptors

import android.graphics.Color
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arpit.collegeuser.Model.viewPagerData
import com.arpit.collegeuser.R


class viewPagerAdaptor(val itemList:ArrayList<viewPagerData>):RecyclerView.Adapter<viewPagerAdaptor.viewpagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewpagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_view_pager_layout, parent, false)
        view.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return viewpagerHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: viewpagerHolder, position: Int) {
        val text  = itemList[position]
        holder.branchImage.setImageResource(text.branchImage)
        holder.branchDesc.text = text.branchDesc
        holder.branchTitle.text= text.branchTitle

        if (position==0){
            holder.branchTitle.setTextColor(Color.parseColor("#ffffff"))
        }
        else if (position==1){
            holder.branchTitle.setTextColor(Color.parseColor("#eb532d"))
        }
        else if (position==2){
            holder.branchTitle.setTextColor(Color.parseColor("#567845"))
        }
        else if (position==3){
            holder.branchTitle.setTextColor(Color.parseColor("#ffffff"))
        }


    }
    class viewpagerHolder(view: View):RecyclerView.ViewHolder(view){
       val branchImage:ImageView = view.findViewById(R.id.BranchImage)
        val branchTitle:TextView=view.findViewById(R.id.branchTitle)
        val branchDesc:TextView = view.findViewById(R.id.branchdesc)
    }


}
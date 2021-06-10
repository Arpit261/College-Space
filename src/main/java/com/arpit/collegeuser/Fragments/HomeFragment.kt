package com.arpit.collegeuser.Fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.arpit.collegeuser.Adaptors.viewPagerAdaptor
import com.arpit.collegeuser.Model.viewPagerData
import com.arpit.collegeuser.R
import com.smarteist.autoimageslider.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.single_view_pager_layout.*


class HomeFragment : Fragment() {

    private lateinit var slider: SliderLayout
    lateinit var adaptor: viewPagerAdaptor
    lateinit var viewpager: ViewPager2



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewpager = view.findViewById(R.id.viewPager2)

        val BranchData = arrayListOf(
                viewPagerData(R.drawable.computer, "Computer Science", "Computer Science and Engineering is the scientific and practical approach to computation and its applications."),
                viewPagerData(R.drawable.electrical, "Electrical Engineering", "Electrical Engineering is a field of engineering that generally deals with the study and applications of electricity."),
                viewPagerData(R.drawable.civil, "Civil Engineering", "Civil engineering is a professional engineering discipline that deals with the design, construction of the physical  built environment."),
                viewPagerData(R.drawable.mechanical, "Mechanical Engineering", "Mechanical Engineering is a creative discipline that grows upon a number of basics and optimizes the devices,machines that involve mechanical forces."))



        adaptor = viewPagerAdaptor(BranchData)

                viewpager.adapter = adaptor



        slider = view.findViewById(R.id.imageSlider)
        slider.setIndicatorAnimation(IndicatorAnimations.DROP)
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        slider.scrollTimeInSec = 1

        sliderViewdata()
        return view
    }

    private fun sliderViewdata() {

        for (i in 1..5) {
            val sliderView = DefaultSliderView(activity as Context)

            when (i) {

                1 -> sliderView.imageUrl = "https://aietm.com/images/main-slider/college/slide-01.jpg"
                2 -> sliderView.imageUrl = "https://aietm.com/images/aryagallery/img14.jpg"
                3 -> sliderView.imageUrl = "https://www.campusoption.com/images/colleges/gallery/29_12_16_061525_banner17.jpg"
                4 -> sliderView.imageUrl = "https://aietm.com/images/aryagallery/img26.jpg"
                5 -> sliderView.imageUrl = "https://aietm.com/images/main-slider/college/slide-5.jpg"

            }
            slider.addSliderView(sliderView)
        }
    }
}

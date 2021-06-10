package com.arpit.collegeuser.Model

data class NoticeModel(val Imageurl:String="",val date:String="",val time:String="",val Branch:String="") {}

data class guesspaper(val docsTittle:String = "",val DocsUrl:String="",val DocsUnit:String="") {}

data class classNotes(val PdfUrl:String="",val PdfUnit:String="",val PdfName:String="") {}

data class AssignmentModel(val imagesUrl:String = "",val date:String="",val time:String=""){}

data class viewPagerData(val branchImage:Int, val branchTitle:String, val branchDesc: String)
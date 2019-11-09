//package com.example.awesomemovies
//
//import android.content.Context
//import android.graphics.Color
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import com.example.awesomemovies.data.model.Movie
//import java.util.zip.Inflater
//import android.view.LayoutInflater
//import kotlinx.android.synthetic.main.main_fragment.*
////import com.github.mikephil.charting.data.PieData
////import com.github.mikephil.charting.data.PieDataSet
////import com.github.mikephil.charting.data.PieEntry
//import kotlinx.android.synthetic.main.movies_fragment.view.*
//import kotlinx.android.synthetic.main.movies_grid_item.view.*
//
//class MainAdapter: BaseAdapter {
//    var list = ArrayList<Movie>()
//    var context: Context? = null
//
//    constructor(context: Context, items: ArrayList<Movie>) : super() {
//        this.context = context
//        this.list = items
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return list[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val item = list[position]
//        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var gridItemView = inflator.inflate(R.layout.movies_grid_item)
//        gridItemView.categoryName.text = list[position].category.name
//        gridItemView.progressBar.progress = (item.tasksDone.toFloat() / item.tasksNumber.toFloat()) * 100
//        gridItemView.progressBar.backgroundStrokeColor = Color.GRAY
//        gridItemView.progressBar.foregroundStrokeColor = Color.BLUE
//        gridItemView.progressBar.backgroundStrokeWidth = 25f
//        gridItemView.progressBar.foregroundStrokeWidth = 30f
//        gridItemView.progressBar.isDrawBackgroundStroke = true
//        gridItemView.chartPercentage.text = "" + gridItemView.progressBar.progress.toInt() + "%"
//
//
//        return gridItemView
//    }
//
//    fun setListOfItems(values : ArrayList<CategoryGridItem>) {
//        list = values
//        notifyDataSetChanged()
//    }
//}
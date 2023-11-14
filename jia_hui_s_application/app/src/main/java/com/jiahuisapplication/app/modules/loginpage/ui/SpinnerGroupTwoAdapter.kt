package com.jiahuisapplication.app.modules.loginpage.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.`annotation`.LayoutRes
import com.jiahuisapplication.app.R
import com.jiahuisapplication.app.modules.loginpage.`data`.model.SpinnerGroupTwoModel
import kotlin.Int
import kotlin.collections.List

class SpinnerGroupTwoAdapter(
  private val mContext: Context,
  @LayoutRes
  private val layoutResource: Int,
  private val allItems: List<SpinnerGroupTwoModel>
) : ArrayAdapter<SpinnerGroupTwoModel>(mContext, layoutResource, allItems) {
  override fun getView(
    position: Int,
    convertView: View?,
    parent: ViewGroup
  ): View = this.createView(position, convertView, parent)

  override fun getDropDownView(
    position: Int,
    convertView: View?,
    parent: ViewGroup
  ): View = this.createView(position, convertView, parent)

  private fun createView(
    position: Int,
    convertView: View?,
    parent: ViewGroup
  ): View {
    val item = getItem(position)
    val view = convertView ?: LayoutInflater.from(mContext)
    .inflate(layoutResource, parent, false)
    view.findViewById<TextView>(R.id.spinnerGroupTwo).text = item?.itemName
    return view
  }
}

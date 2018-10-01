package com.nerderylabs.jbarbatsflickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_list_item.view.*
import nerderylabs.com.jbarbatsflickr.R


class PhotoAdapter(val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var photos: List<Photo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Need to notify the adapter that the data set changed- need to do this when the list gets updated.

    // Gets the # ofimages in the list.
    override fun getItemCount(): Int {
        return photos.size
    }

    // Inflates the item views.
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false))

    }

    // Binds each image to a view in the ArrayList
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var imageUri = photos.get(position).id

        holder?.photoTitle?.text = photos.get(position).title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoImage = view.photo_image
        val photoTitle = view.photo_title
    }
}
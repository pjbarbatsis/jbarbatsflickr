package nerderylabs.com.jbarbatsflickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.photo_list_item.view.*


class PhotoAdapter(var photos: List<Photo>, val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

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
        holder?.photoTitle?.text = photos.get(position).title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoImage = view.photo_image
        val photoTitle = view.photo_title
    }
}
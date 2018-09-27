package nerderylabs.com.jbarbatsflickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.photo_list_item.view.*


class PhotoAdapter(val photos: ArrayList<nerderylabs.com.jbarbatsflickr.Photo>, val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    // Gets the # ofimages in the list.
    override fun getItemCount(): Int {
        return photos.size
    }

    // Inflates the item views.
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false))

    }

    // Binds each image to a view in the ArrayList
    //TODO figure out what i need to extend from the imageType
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //holder?.imageType?.//TODO = photos.get(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageType = view.image_type
    }
}
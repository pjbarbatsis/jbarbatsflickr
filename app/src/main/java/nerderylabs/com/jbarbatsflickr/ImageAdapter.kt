package nerderylabs.com.jbarbatsflickr

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.image_list_item.view.*


class ImageAdapter(val images: ArrayList<nerderylabs.com.jbarbatsflickr.Image>, val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    // Gets the # ofimages in the list.
    override fun getItemCount(): Int {
        return images.size
    }

    // Inflates the item views.
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false))

    }

    // Binds each image to a view in the ArrayList
    //TODO figure out what i need to extend from the imageType
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //holder?.imageType?.//TODO = images.get(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageType = view.image_type
    }
}
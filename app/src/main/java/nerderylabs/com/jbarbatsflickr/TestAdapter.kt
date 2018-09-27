package nerderylabs.com.jbarbatsflickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.image_list_item.view.*
import kotlinx.android.synthetic.main.test_list_item.view.*

class TestAdapter(val strings: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return strings.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvAnimalType?.text = strings.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.test_list_item, parent, false))
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.test_text
}
package nerderylabs.com.jbarbatsflickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


class MainActivity : AppCompatActivity() {

    val photos: ArrayList<Photo> = ArrayList()

    interface FlickrService {

        @GET("services/rest/?method=flickr.galleries.getPhotos")
        fun requestImages(@Query("api_key") api_key: String, @Query("gallery_id") gallery_id: String): Observable<Photo>

        /* Creation of the service
         Source: https://segunfamisa.com/posts/using-retrofit-on-android-with-kotlin
        */
        companion object Factory {
            fun create(): FlickrService {
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.flickr.com/")
                        .build()

                // What's the :: syntax for?
                return retrofit.create(FlickrService::class.java);
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: Check here to see if the most recent data that's older than 3 hours. If it is, do the api call

        //api call

        val apiService = FlickrService.create()

        addImages(jsonParse(apiService))
        flickr_image_list.layoutManager = LinearLayoutManager(this)
        flickr_image_list.adapter = PhotoAdapter(photos, this)

    }

    fun jsonParse(flickrService: FlickrService): JsonArray {

        flickrService.requestImages(getString(R.string.api_key), getString(R.string.gallery_id))

        return JsonArray()
    }




    //TODO: iterate from the flickr gson/json and add the photos to the recyclerview
    fun addImages(jsonArray: JsonArray) {


    }
}

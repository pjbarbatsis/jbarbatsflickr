package nerderylabs.com.jbarbatsflickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.telecom.Call
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MainActivity : AppCompatActivity() {

    val images: ArrayList<Image> = ArrayList()
    val testArray: ArrayList<String> = ArrayList()


    // Should this be here??
    interface FlickrService {

        //Let's test out retrofit
        @GET("flickr.galleries.getPhotos")
        fun requestImages(api_key: String, gallery_id: String): Call

        /* Creation of the service
        Q: What's this companion object stuff?
         Source: https://segunfamisa.com/posts/using-retrofit-on-android-with-kotlin
        */
        companion object Factory {
            fun create(): FlickrService {
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl("http://www.flickr.com")
                        .addConverterFactory(GsonConverterFactory.create())
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
        apiService.requestImages(R.string.api_key.toString(), R.string.gallery_id.toString())

        addImages()
        //addTests()
        flickr_image_list.layoutManager = LinearLayoutManager(this)
        flickr_image_list.adapter = ImageAdapter(images, this)
        //flickr_image_list.adapter = TestAdapter(testArray, this)

    }


    //TODO: iterate from the flickr gson/json and add the images to the recyclerview
    fun addImages() {

    }

    fun addTests() {
        testArray.add("Pop quiz")
        testArray.add("SAT")
        testArray.add("ACT")
        testArray.add("Midterms")
        testArray.add("Finals")
        testArray.add("Diarogu chekku")
        testArray.add("Ooraru Intabyuu")
    }
}

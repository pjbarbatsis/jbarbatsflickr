package nerderylabs.com.jbarbatsflickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity : AppCompatActivity() {


    //not gonna be null, just create it later, makes it so you don't have to make a class that requires context (or whatever else) nullable.
    lateinit var photoAdapter: PhotoAdapter

    interface FlickrService {

        @GET("services/rest/?method=flickr.galleries.getPhotos")
        fun requestImages(@Query("api_key") apiKey: String, @Query("gallery_id") galleryId: String): Observable<PhotosResponse>

        /* Creation of the service
         Source: https://segunfamisa.com/posts/using-retrofit-on-android-with-kotlin
        */
        companion object Factory {
            fun create(): FlickrService {

                // Instantiate a Gson and pass it through the factory's create method to prevent MalformedJsonException
                val gson = GsonBuilder().setLenient().create()

                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl("https://api.flickr.com/")
                        // This is for catching the json and logging it in debug, network stuff handled by okhttp
                        .client(OkHttpClient
                                .Builder()
                                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor
                                        .Logger { message -> Log.d("okhttp", message) }))
                                .build())
                        .build()


                return retrofit.create(FlickrService::class.java);
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoAdapter = PhotoAdapter(emptyList<Photo>(), this)
        flickrImageList.layoutManager = LinearLayoutManager(this)
        flickrImageList.adapter = photoAdapter

        //api call
        // if NetworkOnMainThreadException occurs, be sure to use subscribeOn and observeOn to point out threads
        val apiService = FlickrService.create()
        apiService.requestImages(getString(R.string.api_key), getString(R.string.gallery_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: PhotosResponse? ->
                    // Assigning the response- serialized names are ways to reassign names to confusing json fields
                    photoAdapter.photos = response?.result!!.photos
                })
    }
}

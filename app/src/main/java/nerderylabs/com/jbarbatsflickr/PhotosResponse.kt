package nerderylabs.com.jbarbatsflickr

import com.google.gson.annotations.SerializedName

// Data class that handles the responses and optionally renames the fields if they suck
data class PhotosResponse(@SerializedName("photos") val result: Result) {
    data class Result(@SerializedName("photo") val photos: List<Photo>)
}
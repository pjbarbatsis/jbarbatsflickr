package com.nerderylabs.jbarbatsflickr.helpers

import com.google.gson.annotations.SerializedName
import com.nerderylabs.jbarbatsflickr.model.Photo

// Data Class that handles the responses and optionally renames the fields if they suck
data class PhotosResponse(@SerializedName("photos") val result: Result) {
    data class Result(@SerializedName("photo") val photos: List<Photo>)
}
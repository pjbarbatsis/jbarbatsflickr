package com.nerderylabs.jbarbatsflickr.model


// Populate this with fields from the GSON/JSON whatever
// possibly null, only put what you need
data class Photo(
        val id: String,
        val title: String,
        val secret: String,
        val server: String,
        val farm: String
)
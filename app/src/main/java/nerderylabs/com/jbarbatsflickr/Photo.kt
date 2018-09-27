package nerderylabs.com.jbarbatsflickr


// Populate this with fields from the GSON/JSON whatever
data class Photo(
        val url: String,
        val width: Int,
        val height: Int,
        val isPrimary: Boolean,
        val isVideo: Boolean
)
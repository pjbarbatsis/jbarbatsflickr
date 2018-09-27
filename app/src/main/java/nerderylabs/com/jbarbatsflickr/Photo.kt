package nerderylabs.com.jbarbatsflickr


// Populate this with fields from the GSON/JSON whatever
data class Photo(
        val id: Int,
        val owner: String,
        val secret: String,
        val server: Int,
        val farm: Int,
        val title: String,
        val isPublic: Boolean,
        val isFriend: Boolean,
        val isFamily: Boolean
)
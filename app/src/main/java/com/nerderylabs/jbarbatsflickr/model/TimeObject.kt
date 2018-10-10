package com.nerderylabs.jbarbatsflickr.model

import android.content.ContentValues
import io.requery.*
import org.joda.time.DateTime

class TimeObject(id: Int, time: DateTime) {

    var id: Int = 0
    var time: DateTime? = null

}
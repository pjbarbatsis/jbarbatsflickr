package com.nerderylabs.jbarbatsflickr.model

import android.content.ContentValues
import io.requery.*
import org.joda.time.DateTime

data class TimeObject(var id: Int, var time: DateTime?)
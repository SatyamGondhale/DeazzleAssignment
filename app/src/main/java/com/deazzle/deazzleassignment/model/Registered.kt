package com.deazzle.deazzleassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.concurrent.Executors

class Registered {
    @SerializedName("date")
    @Expose
    var dateofregistration: String? = null

    @SerializedName("age")
    @Expose
    var age: Int? = null

}
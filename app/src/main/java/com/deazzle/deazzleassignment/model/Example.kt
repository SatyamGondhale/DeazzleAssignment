package com.deazzle.deazzleassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Example {
    @SerializedName("results")
    @Expose
    public var results: List<Result> = ArrayList()

}
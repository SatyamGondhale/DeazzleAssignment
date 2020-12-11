package com.deazzle.deazzleassignment.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deazzle.deazzleassignment.model.*

@Entity(tableName = "randomuser")
class RandomUser(@field:ColumnInfo(name = "gender") var gender: String, @field:Embedded var name: Name, @field:Embedded var location: Location, @field:ColumnInfo(name = "email") var email: String, @field:Embedded var login: Login, @field:Embedded(prefix = "user_dob") var dob: Dob, @field:Embedded var registered: Registered, @field:ColumnInfo(name = "phone") var phone: String, @field:Embedded var userId: Id, @field:Embedded var picture: Picture, @field:ColumnInfo(name = "nat") var nationallity: String) {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
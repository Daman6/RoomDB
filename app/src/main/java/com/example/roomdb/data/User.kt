package com.example.roomdb.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val state: String,
    val city: String,
    val pincode: Int,
    val mobileNo: Int,
    val aadharNo: Int
):Parcelable
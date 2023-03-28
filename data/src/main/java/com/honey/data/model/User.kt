package com.honey.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constance.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val pictureUrl: String,
    @ColumnInfo val name: String,
    @ColumnInfo val email: String,
    @ColumnInfo val birthday: String,
    @ColumnInfo val address: String,
    @ColumnInfo val number: String,
    @ColumnInfo val password: String
)

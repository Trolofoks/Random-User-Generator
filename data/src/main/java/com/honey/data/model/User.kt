package com.honey.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.honey.data.saved.storage.sql.Constance


@Entity(tableName = Constance.TABLE_NAME)
data class User(

    @ColumnInfo val pictureUrl: String,
    @PrimaryKey val name: String,
    @ColumnInfo val email: String,
    @ColumnInfo val birthday: String,
    @ColumnInfo val address: String,
    @ColumnInfo val number: String,
    @ColumnInfo val password: String,
    @ColumnInfo val picture: ByteArray? = null
)

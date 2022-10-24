package com.example.catatan_taufan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// menentukan nama table
@Entity(tableName = "notesTable")

// menentukan info kolom dan type datanya
class Note (
    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String
    ) {
    // menentukan primary key dan menentukan nilai awal yaitu 0
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
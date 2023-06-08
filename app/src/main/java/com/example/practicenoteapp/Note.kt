package com.example.practicenoteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
class Note (
    @ColumnInfo("notetitle")val noteTitle :String,
    @ColumnInfo("notedescription")val noteDescription :String,
    @ColumnInfo("notetime")val noteTime:String
        ){
    @PrimaryKey(autoGenerate = true) var id =0
}
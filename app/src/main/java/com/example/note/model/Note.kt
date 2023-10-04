package com.example.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "Title") val title: String?,
    @ColumnInfo(name = "Note") val note: String?,
    @ColumnInfo(name = "Date") val date: String?
):java.io.Serializable

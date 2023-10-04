package com.example.note.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("UPDATE notes_table set Title =:title, Note = :note WHERE id =:id ")
    suspend fun update(id: Int?, title: String?, note: String?)

    @Query("SELECT * FROM notes_table order by id DESC")
    fun getAllNotes(): LiveData<List<Note>>

}
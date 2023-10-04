package com.example.note.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note.database.NoteDatabase
import com.example.note.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NotesViewModel (application:Application) : AndroidViewModel(application){

    private val repository : NoteRepository
    val allNotes : LiveData<List<Note>>

     init {
         val dao = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)
         allNotes = repository.allNotes
     }

    fun deleteNote(
        note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(
        note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(
        note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
}
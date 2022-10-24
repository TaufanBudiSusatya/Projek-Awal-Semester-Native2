package com.example.catatan_taufan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal (application: Application) :AndroidViewModel(application) {

   // membuat variabel allnotes dan
    // variabel repository
    val allNotes : LiveData<List<Note>>
    val repository : NoteRepository

    // mendeklarasikan variabel dao,repository, dan allnotes
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // membuat method deletenote untuk menghapus
    // dan memanggil method deletenote
    // update dari repository
    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // membuat method update untuk memperbarui dan memanggil method update
    // update dari repository
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    //membuat method addnote untuk menambahkan data baru
    // dan memanggil method addnote dari repository untuk menambahkan data
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}
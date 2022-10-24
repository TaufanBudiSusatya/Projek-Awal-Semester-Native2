package com.example.catatan_taufan

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {

    // membuat variable untuk live data dan menadapatkan semua note
    // dari kelas DAO
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    // membuat method insert untuk menambahkan data ke dalam database
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    // membuat method delete untuk menghapus data
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    // membuat method update untuk memperbarui data
    suspend fun update(note: Note){
        notesDao.update(note)
    }
}
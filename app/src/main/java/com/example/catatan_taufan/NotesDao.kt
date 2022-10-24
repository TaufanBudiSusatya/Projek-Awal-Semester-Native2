package com.example.catatan_taufan

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

   //method insert untuk menambahkan data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(note :Note)

    // method hapusu untuk menghapus data
    @Delete
    fun delete(note: Note)

    // method getallnote dari database dan menentukan query
    // didalam query menampilkan data ascending
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

   // method update untuk memperbarui data
    @Update
    fun update(note: Note)

}
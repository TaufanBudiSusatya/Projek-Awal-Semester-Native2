package com.example.catatan_taufan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    // membuat variable untuk komponen UI
    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button

    // Membuat variable untuk view model dan
    // untuk note id bertype integer
    lateinit var viewModal: NoteViewModal
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        // mulai masuk kedalam viiew model
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // mendeklarasikan semua variable yang di butuhkan.
        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)

        // mendapatkan data dan diteruskan dengan cara intent.
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            // mengatur data untuk mengedit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
        } else {
            saveBtn.setText("Simpan Note")
        }

        // menambahkan button untuk menyimpan
        saveBtn.setOnClickListener {
            // mendapatkan judul dan deskripsi dari edit teks
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdt.text.toString()
            // memeriksa jenis dari yang di masukkan
            //lalu menyimpan atau memperbaharui data
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "$noteTitle Diperbarui..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // jika type string tidak kosong maka akan
                    // menambahkan data ke dalam room database
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, " Menambahkan $noteTitle", Toast.LENGTH_LONG).show()
                }
            }
            // membuka atau berpindah ke aktivitas baru
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
        //memberi nama pada bagian action bar
        // dan membuat tombol kembali ke activity sebelumnya
       supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Tambah Catatan"
    }
}
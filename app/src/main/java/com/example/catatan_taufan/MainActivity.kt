package com.example.catatan_taufan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    // membuat variable untuk tampilan recycle view,
    //button dan view model
    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mendeklarasikan semua varibablee
        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        // mengatur tampilan untuk recycle view
        notesRV.layoutManager = LinearLayoutManager(this)

        // mendeklarasikan kelas adapter
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        // mengatur adapter ke recycle view
        notesRV.adapter = noteRVAdapter

        // mendeklarasikan view model
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // memanggil method dari kelas view model
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // memperbarui datalist
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // menambahkan click listener dan membuka intent untuk
            //menambahkan data
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteClick(note: Note) {
        // meneruskan data ke file addeditnote
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        // membuat perintah untuk memanggil method hapus dari view model
        viewModal.deleteNote(note)
        // menampilkan toast
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}
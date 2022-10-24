package com.example.catatan_taufan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    // membuat semua variable untuk allnotes
    private val allNotes = ArrayList<Note>()

    // membuat kelas view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // mendeklrasikan semua variable yang telah di tambahkan
        // di file layout
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // mengembangkan file layout di setiap item recyle view
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // menyetel data ke recyleview
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText(" " + allNotes.get(position).timeStamp)
        // menambahkan click listener untuk button hapus
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        // menambahkan click listener ke recycle view
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
       //mengembalikan ukuran list
        return allNotes.size
    }

    // method updatelist berfungsi untuk memperbarui data
    fun updateList(newList: List<Note>) {
        // all notes berfungsi untuk membersihkan data
        allNotes.clear()
        // menambahkan daftar baru ke allnotes
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    // membuat method untuk tindakan menghapus pada gambar 'hapus'
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    // membuat method untuk mengupdate data
    fun onNoteClick(note: Note)
}
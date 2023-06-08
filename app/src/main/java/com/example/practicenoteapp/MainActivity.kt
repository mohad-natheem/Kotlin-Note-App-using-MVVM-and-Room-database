package com.example.practicenoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView.RecyclerListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),NoteClickInterface,NoteClickDeleteInterface {
    lateinit var noteRv:RecyclerView
    lateinit var FAB : FloatingActionButton
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRv= findViewById(R.id.Note_rv)
        FAB = findViewById(R.id.note_fb)

        noteRv.layoutManager =LinearLayoutManager(this)

        val RvAdapter = NoteRVAdapter(this,this,this)

        noteRv.adapter = RvAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                RvAdapter.updateList(it)
            }
        })
        FAB.setOnClickListener{
            val intent = Intent(this@MainActivity,AddNote::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,AddNote::class.java)
        intent.putExtra("note_type","Edit")
        intent.putExtra("note_title",note.noteTitle)
        intent.putExtra("note_desc",note.noteDescription)
        intent.putExtra("note_id",note.id)
        startActivity(intent)
        this.finish()

    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()

    }
}

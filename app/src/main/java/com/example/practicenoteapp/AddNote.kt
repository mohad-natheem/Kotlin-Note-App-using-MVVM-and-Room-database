package com.example.practicenoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    lateinit var etTitle :EditText
    lateinit var etNote:EditText
    lateinit var saveBtn :Button

    lateinit var viewModel: NoteViewModel
    var note_id =-1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        etTitle= findViewById(R.id.et_note_title)
        etNote = findViewById(R.id.et_note_desc)
        saveBtn=findViewById(R.id.add_button)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("note_type")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("note_title")
            val noteDesc = intent.getStringExtra("note_desc")
            note_id = intent.getIntExtra("note_id",-1)
            saveBtn.setText("Update Note")
            etTitle.setText(noteTitle)
            etNote.setText(noteDesc)
        }
        else{
            saveBtn.setText("Save Note")
        }
        saveBtn.setOnClickListener {

            val noteTitle = etTitle.text.toString()
            val noteDesc = etNote.text.toString()
            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updatedNote = Note(noteTitle,noteDesc,currentDate)
                    updatedNote.id=note_id
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this,"Note Updated...",Toast.LENGTH_LONG).show()



                }



            }else{
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewModel.insertNote(Note(noteTitle,noteDesc,currentDate))
                    Toast.makeText(this,"Note Added...",Toast.LENGTH_LONG).show()


                }
            }

            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }




    }
}
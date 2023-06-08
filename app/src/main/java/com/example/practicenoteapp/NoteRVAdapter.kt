package com.example.practicenoteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface:NoteClickDeleteInterface,
    val noteClickInterface:NoteClickInterface
):RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {
    private val allNotes= ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return NoteViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.note_title.setText(allNotes.get(position).noteTitle)
        holder.note_time.setText("Last Updated : "+allNotes.get(position).noteTime)
        holder.delete_img.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))

        }

        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }


    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val note_title = itemView.findViewById<TextView>(R.id.note_title)
        val note_time = itemView.findViewById<TextView>(R.id.note_update_time)
        val delete_img = itemView.findViewById<ImageView>(R.id.delete_img)

    }
    fun updateList(newlist:List<Note>){
        allNotes.clear()
        allNotes.addAll(newlist)
        notifyDataSetChanged()
    }
}
interface NoteClickDeleteInterface{

    fun onDeleteIconClick(note: Note)
}
interface NoteClickInterface{
    fun onNoteClick(note:Note)
}
package com.example.note.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.model.Note
import kotlin.random.Random

class NotesAdapter(private val context:Context, val listener: NotesClickListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_items,parent,false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.noteDisc.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notesLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        holder.notesLayout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notesLayout.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition],holder.notesLayout)
            true
        }
    }

    private fun randomColor() : Int{
        val colorList = ArrayList<Int>()
        colorList.add(R.color.noteColor1)
        colorList.add(R.color.noteColor2)
        colorList.add(R.color.noteColor3)
        colorList.add(R.color.noteColor4)
        colorList.add(R.color.noteColor5)
        colorList.add(R.color.noteColor6)
        colorList.add(R.color.noteColor7)
        colorList.add(R.color.noteColor8)
        colorList.add(R.color.noteColor9)
        colorList.add(R.color.noteColor10)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colorList.size)

        return colorList[randomIndex]
    }

    fun UpdateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun FilterList(search:String){

        notesList.clear()
        for (item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true){
                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val notesLayout = itemView.findViewById<CardView>(R.id.notesLayout)
        val title = itemView.findViewById<TextView>(R.id.noteTitle)
        val noteDisc = itemView.findViewById<TextView>(R.id.noteDisc)
        val date = itemView.findViewById<TextView>(R.id.noteTime)

    }

    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note,cardView: CardView)
    }
}
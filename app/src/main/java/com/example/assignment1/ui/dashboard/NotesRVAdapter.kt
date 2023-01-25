package com.example.assignment1.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.R

class NotesRVAdapter : ListAdapter<Record, NotesRVAdapter.NoteHolder>(DiffCallback()) {

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.single_note, parent, false)
        val noteHolder = NoteHolder(v)

        val noteDelete = noteHolder.itemView.findViewById<ImageView>(R.id.note_delete)
        noteDelete.setOnClickListener {
            listener.onItemRemoveClick(noteHolder.adapterPosition)
        }

        val note = noteHolder.itemView.findViewById<CardView>(R.id.note)
        note.setOnClickListener {
            listener.onItemClick(noteHolder.adapterPosition)
        }

        return noteHolder
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentItem = getItem(position)
        val noteName = holder.itemView.findViewById<TextView>(R.id.note_name)
        noteName.text = currentItem.NAME
        val noteText = holder.itemView.findViewById<TextView>(R.id.note_text)
        noteText.text = currentItem.DESCRIPTION
    }

    class DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Record, newItem: Record) =
            oldItem == newItem
    }
}

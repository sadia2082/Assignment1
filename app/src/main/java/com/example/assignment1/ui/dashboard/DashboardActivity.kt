package com.example.assignment1.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.R
import com.example.assignment1.ui.addrecord.AddNoteActivity
import kotlinx.coroutines.launch
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: NotesRVAdapter

    private val noteDatabase by lazy {RecordDatabase.getDatabase(this).RecordDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_fragment)

        setRecyclerView()

        observeNotes()
    }

    private fun setRecyclerView() {
        val notesRecyclerview = findViewById<RecyclerView>(R.id.notes_recyclerview)
        notesRecyclerview.layoutManager = LinearLayoutManager(this)
        notesRecyclerview.setHasFixedSize(true)
        adapter = NotesRVAdapter()
        adapter.setItemListener(object : RecyclerClickListener {

            // Tap the 'X' to delete the note.
            override fun onItemRemoveClick(position: Int) {
                val notesList = adapter.currentList.toMutableList()
                val noteName = notesList[position].NAME
                val noteDescription = notesList[position].DESCRIPTION
                val removeNote = Record(noteName, noteDescription)
                notesList.removeAt(position)
                adapter.submitList(notesList)
                lifecycleScope.launch {
                    noteDatabase.deleteRecord(removeNote)
                }
            }

            // Tap the note to edit.
            override fun onItemClick(position: Int) {
                val intent = Intent(this@DashboardActivity, AddNoteActivity::class.java)
                val notesList = adapter.currentList.toMutableList()
                intent.putExtra("note_name", notesList[position].NAME)
                intent.putExtra("note_text", notesList[position].DESCRIPTION)
                editNoteResultLauncher.launch(intent)
            }
        })
        notesRecyclerview.adapter = adapter
    }

    private fun observeNotes() {
        lifecycleScope.launch {
            noteDatabase.getRecord().collect { notesList ->
                if (notesList.isNotEmpty()) {
                    adapter.submitList(notesList)
                }
            }
        }
    }

    private val newNoteResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the new note from the AddNoteActivity
                val noteName = result.data?.getStringExtra("note_name")
                val noteText = result.data?.getStringExtra("note_text")
                // Add the new note at the top of the list
                val newNote = Record(noteName ?: "", noteText ?: "")
                lifecycleScope.launch {
                    noteDatabase.addRecord(newNote)
                }
            }
        }

    val editNoteResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the edited note from the AddNoteActivity
                val noteName = result.data?.getStringExtra("note_name")
                val noteText = result.data?.getStringExtra("note_text")
                // Update the note in the list
                val editedNote =  Record(noteName ?: "", noteText ?: "")
                lifecycleScope.launch {
                    noteDatabase.updateRecord(editedNote)
                }
            }
        }


    // The '+' menu button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_record) {
            // Open AddNoteActivity
            val intent = Intent(this, AddNoteActivity::class.java)
            newNoteResultLauncher.launch(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }
}
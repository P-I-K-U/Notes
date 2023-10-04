package com.example.note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note.databinding.ActivityAddNoteBinding
import com.example.note.model.Note
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note // new updated note
    private lateinit var oldNote: Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.addTitle.setText(oldNote.title)
            binding.writeNote.setText(oldNote.note)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.done.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val noteDisc = binding.writeNote.text.toString()

            if (title.isNotEmpty() || noteDisc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE,d MM yyyy HH:mm a")
                if (isUpdate) {
                    note = Note(
                        oldNote.id, title, noteDisc, formatter.format(Date())
                    )
                } else {
                    val formatter2 = SimpleDateFormat("EEE,d MM yyyy HH:mm a")
                    note = Note(
                        null, title, noteDisc, formatter2.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddNote, "Please enter some data!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


        }
        binding.back.setOnClickListener {

            onBackPressed()

        }
    }


}
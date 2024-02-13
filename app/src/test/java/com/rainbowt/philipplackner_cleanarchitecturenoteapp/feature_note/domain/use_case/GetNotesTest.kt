package com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }

        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(it)
            }
        }
    }
}
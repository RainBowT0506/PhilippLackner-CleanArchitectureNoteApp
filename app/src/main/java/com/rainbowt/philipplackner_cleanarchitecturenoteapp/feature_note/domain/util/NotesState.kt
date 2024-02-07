package com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.util

import com.rainbowt.philipplackner_cleanarchitecturenoteapp.feature_note.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
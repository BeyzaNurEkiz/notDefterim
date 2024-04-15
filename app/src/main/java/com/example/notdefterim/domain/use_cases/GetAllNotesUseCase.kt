package com.example.notdefterim.domain.use_cases

import com.example.notdefterim.data.local.model.Note
import com.example.notdefterim.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> =
        repository.getAllNotes()
}
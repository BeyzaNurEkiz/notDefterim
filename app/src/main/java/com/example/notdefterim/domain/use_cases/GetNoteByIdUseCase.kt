package com.example.notdefterim.domain.use_cases

import com.example.notdefterim.domain.repository.Repository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id:Long) = repository.getNoteById(id)
}
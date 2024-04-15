package com.example.notdefterim.domain.use_cases

import com.example.notdefterim.data.local.model.Note
import com.example.notdefterim.domain.repository.Repository
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) = repository.insert(note)
}


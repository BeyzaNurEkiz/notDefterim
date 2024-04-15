package com.example.notdefterim.domain.use_cases

import com.example.notdefterim.domain.repository.Repository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id:Long)=repository.delete(id)
//invoke: sınıfın çağrılabilir olduğunu belirtmek için kullanılır.
}
package com.example.notification.repository

import com.example.notification.domain.Works
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface WoksRepository : CoroutineCrudRepository<Works, String> {
    override suspend fun findById(id:String): Works
}
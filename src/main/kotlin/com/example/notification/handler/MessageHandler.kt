package com.example.notification.handler

import com.example.notification.domain.Works
import com.example.notification.message.Publisher
import org.springframework.stereotype.Component

@Component
class MessageHandler(
    private val publisher : Publisher
) {
    /*private*/
    suspend fun plagiarism(w:Works){
        publisher.plagiarism(w)
    }
}
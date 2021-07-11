package com.example.notification.message

import com.example.notification.domain.Works
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Publisher(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    suspend fun plagiarism(w:Works){
        var msg = ""
        for (i in w.submissions!!){
            if(msg.length > 0)
                msg += ";" + i.submission
            else
                msg = i.submission
        }
        kafkaTemplate.send("plagiarism",msg)
    }
}
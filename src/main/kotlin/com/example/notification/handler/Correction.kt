package com.example.notification.handler

import com.example.notification.message.Publisher
import com.example.notification.repository.WoksRepository
import org.springframework.kafka.core.KafkaTemplate

class Correction {
    suspend fun corretion(repository: WoksRepository, message: String) {
        val work = repository.findById(message)
        val template = work.template
        for (i in 0..work.submissions?.size!!-1){
            if (work.submissions[i].equals(template)){
                work.submissions[i].note = "10"
                repository.save(work)
            }else{
                if (work.submissions[i].submission.length <= template.length){
                    var note = 0
                    for (j in 0..work.submissions[i].submission.length-1){
                        if(work.submissions[i].submission[j] ==template[j]){
                            note += 1
                        }
                    }
                    note = (10*note)/template.length
                    work.submissions[i].note =""+note
                    repository.save(work)
                }else{
                    var note = 0
                    for (j in 0..template.length-1){
                        if(work.submissions[i].submission[j] == template[j]){
                            note += 1
                        }
                    }
                    note = (10*note)/template.length
                    work.submissions[i].note =""+note
                    repository.save(work)

                }

            }
        }
    }
}
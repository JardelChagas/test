package com.example.notification.message

import com.example.notification.repository.WoksRepository
import org.springframework.kafka.annotation.KafkaListener
import com.example.notification.domain.Submissions
import org.springframework.stereotype.Component
import com.example.notification.domain.Works
import com.example.notification.handler.Correction
import com.example.notification.handler.MessageHandler
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.util.*


@Component
class Subscriber(val repository: WoksRepository,private val messageHandler: MessageHandler) {

    private val log  = LoggerFactory.getLogger(Subscriber::class.java)

    @KafkaListener(
        topics = ["test-create"],
        groupId = "test-create-group"
    )
      fun subscriberMessage(message: String) {
        val list = message.split(";")
        val work = Works(list.get(0),list.get(1), list.get(2))
        log.info("{}, date created: {}", message, Date().time)
        runBlocking { repository.save(work) }
      }
    @KafkaListener(
        topics = ["submit-solution"],
        groupId = "submit-solution-groupid"
    )
    fun submitSolution(message: String) {
        val list = message.split(";")
        val id_student      = list[0]
        val id_work         = list[1]
        val solution_work   = list[2]
        val work_endsIn     = list[3]
        val submission      = Submissions(id_student, solution_work )
        var work:Works
        runBlocking {
            if (repository.findById(id_work) is Works){
                work = repository.findById(id_work)!!
                work.submissions?.add(submission)
                repository.save(work)
            }

        }
        log.info("my id: {}", message)
    }

    @KafkaListener(
        topics = ["corretion"],
        groupId = "corretion-groupid"
    )
    fun corretion(message: String){
        runBlocking {
            Correction().corretion(repository, message)
            messageHandler.plagiarism(repository.findById(message))
        }
    }

}



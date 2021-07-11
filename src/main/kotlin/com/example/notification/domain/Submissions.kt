package com.example.notification.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Submissions (
    @Id
    val id_student  : String,
    val submission  : String,
    var note        : String ?= "-"
)
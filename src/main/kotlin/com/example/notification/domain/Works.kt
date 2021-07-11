package com.example.notification.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Works (
    @Id
    val id          : String,
    val template    : String,
    val endIn       :String,
    val submissions : MutableList<Submissions>?=arrayListOf()
)
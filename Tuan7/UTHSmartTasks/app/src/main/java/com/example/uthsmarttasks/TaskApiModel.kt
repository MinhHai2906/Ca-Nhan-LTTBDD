package com.example.uthsmarttasks


data class ApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<TaskApi>
)

data class TaskDetailResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: TaskApi
)

data class TaskApi(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val dueDate: String,
    val desImageURL: String? = null,
    val category: String? = null,
    val subtasks: List<Subtask>? = emptyList(),     
    val attachments: List<Attachment>? = emptyList() 
)


data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)

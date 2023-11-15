package com.justin.mob21justin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
    val id: String = "",
    val addedBy: String = "justin",
    val createdAt: String = "",
    val desc: String = "",
    val isFinish: Boolean = false,
    val title: String = ""
) {
    fun toHashMap(): HashMap<String, Any> {

        return hashMapOf<String,Any>(
            "title" to title,
            "desc" to desc,
            "addedBy" to addedBy,
            "createdAt" to createdAt,
            "isFinish" to isFinish,
        )
    }

    companion object {
        fun fromHashMap(hash: Map<String,Any>):Todo{
            return Todo(
                id = hash["id"].toString(),
                title = hash["title"].toString(),
                desc = hash["desc"].toString(),
                addedBy = hash["addedBy"].toString(),
                createdAt = hash["createdAt"].toString(),
                isFinish = hash["isFinish"] as Boolean,
            )
        }
    }
}
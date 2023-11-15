package com.justin.mob21justin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justin.mob21justin.data.model.Todo


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        const val DB_NAME = "todo_database"
    }
}
package com.sample.todolist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.todolist.data.dao.TodosDao
import com.sample.todolist.data.model.TodosModel

@Database(entities = [TodosModel::class], version = 1, exportSchema = false)
abstract class TodosDatabase : RoomDatabase() {

    abstract fun todosDao(): TodosDao

    companion object {
        // Singleton prevents multiple instances of database
        @Volatile
        private var INSTANCE: TodosDatabase? = null
        fun init(context: Context): TodosDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, TodosDatabase::class.java, "todos_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }

    }
}
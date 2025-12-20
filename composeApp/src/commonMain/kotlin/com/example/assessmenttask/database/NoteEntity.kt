package org.example.project.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "NoteTable"
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val subtitle: String,
    val date:String = ""
)
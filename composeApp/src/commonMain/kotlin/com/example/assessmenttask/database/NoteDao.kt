package org.example.project.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insetNote(
        noteEntity: NoteEntity
    )

    @Query("SELECT * FROM notetable")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Delete
    suspend fun deleteNote(
        entity: NoteEntity
    )

    @Update
    suspend fun updateNote(
        noteEntity: NoteEntity
    )

    @Query("""
    UPDATE NoteTable
    SET title = :title, subtitle = :subtitle, date = :date
    WHERE id = :id
""")
    suspend fun updateNoteById(id: Int, title: String, subtitle: String, date: String)

}
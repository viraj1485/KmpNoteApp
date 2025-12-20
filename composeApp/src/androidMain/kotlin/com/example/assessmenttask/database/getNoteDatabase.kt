package org.example.project.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getPeopleDatabase(context: Context): NoteDatabase {
    val dbFile = context.getDatabasePath("NoteDatabase.db")
    return Room.databaseBuilder<NoteDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
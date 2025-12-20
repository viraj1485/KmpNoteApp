package org.example.project.nativedatepicker

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

actual fun pickDate(context: Any?, onDatePicked: (String) -> Unit) {

    // Ensure context is of type Context
    if (context !is Context) return

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Android-specific DatePickerDialog
    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDatePicked(date)
        },
        year,
        month,
        day
    ).show()
}
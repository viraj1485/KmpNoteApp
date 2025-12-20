package org.example.project.nativedatepicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIBarButtonItem
import platform.UIKit.UIBarButtonItemStyle
import platform.UIKit.UIDatePicker
import platform.UIKit.UIDatePickerMode
import platform.UIKit.UIDatePickerStyle
import platform.UIKit.UIToolbar
import platform.UIKit.UIView
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformDatePicker(
    show: Boolean,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (!show) return

    UIKitView(
        factory = {
            val picker = UIDatePicker().apply {
                datePickerMode = UIDatePickerMode.UIDatePickerModeDate
                preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
            }

            val formatter = NSDateFormatter().apply {
                dateFormat = "yyyy-MM-dd"
            }

            // DONE button action
            val doneAction = object : NSObject() {
                @ObjCAction
                fun doneTapped() {
                    val dateString = formatter.stringFromDate(picker.date)
                    onDateSelected(dateString)
                }
            }

            // CANCEL button action
            val cancelAction = object : NSObject() {
                @ObjCAction
                fun cancelTapped() {
                    onDismiss()
                }
            }

            val toolbar = UIToolbar().apply {
                sizeToFit()
                items = listOf(
                    UIBarButtonItem(
                        title = "Cancel",
                        style = UIBarButtonItemStyle.UIBarButtonItemStylePlain,
                        target = cancelAction,
                        action = NSSelectorFromString("cancelTapped")
                    ),
                    UIBarButtonItem.flexibleSpaceItem(),
                    UIBarButtonItem(
                        title = "Done",
                        style = UIBarButtonItemStyle.UIBarButtonItemStyleDone,
                        target = doneAction,
                        action = NSSelectorFromString("doneTapped")
                    )
                )
            }

            val container = UIView().apply {
                addSubview(toolbar)
                addSubview(picker)
            }

            container
        }
    )
}
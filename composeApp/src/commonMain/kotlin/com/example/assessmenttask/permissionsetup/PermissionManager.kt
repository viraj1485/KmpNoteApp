package com.example.assessmenttask.permissionsetup

import androidx.compose.runtime.Composable

expect class PermissionManager(callback:PermissionCallback) : PermissionHandler

interface PermissionCallback {
    fun onPermissionStatus(permissionType:PermissionType,status:PermissionStatus)
}

@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionManager
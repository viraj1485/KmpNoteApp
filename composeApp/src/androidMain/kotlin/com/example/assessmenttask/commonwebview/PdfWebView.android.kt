package org.example.project.commonwebview

import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView

@Composable
actual fun pdfViewerWebView(url: String) {
    WebViewPhysioScreen(url)
}

@Composable
fun WebViewPhysioScreen(
    url: String
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {

                // Enable settings
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                webViewClient = WebViewClient()

                // Load URL
                loadUrl(
                    "https://docs.google.com/gview?embedded=true&url=$url"
                )
            }
        }
    )
}

package org.example.project.commonwebview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@Composable
actual fun pdfViewerWebView(url: String) {

    UIKitView(modifier = Modifier.fillMaxSize(),
        factory = {
            WKWebView()
        },
        update = { webView ->;
            webView.loadRequest(
                NSURLRequest.requestWithURL(
                    NSURL.URLWithString(url)!!
                )
            )
        }
    )
}
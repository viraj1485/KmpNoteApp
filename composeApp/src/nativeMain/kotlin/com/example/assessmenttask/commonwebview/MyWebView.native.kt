package org.example.project.commonwebview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIView
import platform.UIKit.UIViewAutoresizingFlexibleHeight
import platform.UIKit.UIViewAutoresizingFlexibleWidth
import platform.WebKit.WKNavigationAction
import platform.WebKit.WKNavigationActionPolicy
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKNavigationTypeLinkActivated
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MyWebView(
    htmlContent: String,
    isLoading: (isLoading: Boolean) -> Unit,
    onUrlClicked: (url: String) -> Unit
) {
    val content = remember { htmlContent }
    val webView = remember { WKWebView() }

    // Define the WKNavigationDelegate
    val navigationDelegate = rememberWebViewDelegate(onUrlClicked)

    // Set WKNavigationDelegate to the webView
    webView.navigationDelegate = navigationDelegate
    UIKitView(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        factory = {
            val container = UIView()

            val config = WKWebViewConfiguration().apply {
                allowsInlineMediaPlayback = true
                allowsAirPlayForMediaPlayback = true
                allowsPictureInPictureMediaPlayback = true
            }

            val webView = WKWebView(frame = CGRectZero.readValue(), configuration = config)
            webView.loadHTMLString(content, baseURL = null)

            container.addSubview(webView)

            // Important: autoresizing
            webView.autoresizingMask =
                UIViewAutoresizingFlexibleWidth or UIViewAutoresizingFlexibleHeight

            webView.setFrame(container.bounds)

            container
        },
        update = { container ->
            val webView = container.subviews.first() as WKWebView
            webView.setFrame(container.bounds)
        }
    )

}

@Composable
private fun rememberWebViewDelegate(onUrlClicked: (String) -> Unit): WKNavigationDelegateProtocol {
    return object : NSObject(), WKNavigationDelegateProtocol {
        override fun webView(
            webView: WKWebView,
            decidePolicyForNavigationAction: WKNavigationAction,
            decisionHandler: (WKNavigationActionPolicy) -> Unit
        ) {
            val navigationType = decidePolicyForNavigationAction.navigationType
            val request = decidePolicyForNavigationAction.request

            when (navigationType) {
                WKNavigationTypeLinkActivated -> {
                    // Handle link clicks
                    if (decidePolicyForNavigationAction.targetFrame == null) {
                        // Load the link in the same WKWebView
                        webView.loadRequest(request)
                    }
                    onUrlClicked(request.URL?.absoluteString ?: "")
                    println(request.URL?.absoluteString ?: "")
                    decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
                }

                else -> decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
            }
        }
    }
}
package com.heyoh.prolog.util

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

object DownloadUtil {
    fun downloadPDF(URL:String, fileName:String, context: Context){
        val request = DownloadManager.Request(Uri.parse(URL))
        request.setTitle(fileName)
        request.setDescription("Descargando $fileName")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName)
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}
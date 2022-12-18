package com.github.sunrise.tinyid.client.utils

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class TinyIdHttpUtils {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        fun post(
            url: String,
            readTimeout: Int,
            connectTimeout: Int,
            form: MutableMap<String, String>? = null
        ): String? {
            var conn: HttpURLConnection? = null
            var os: OutputStreamWriter? = null
            var rd: BufferedReader? = null
            var line: String?
            var respose: String? = null
            val param = StringBuilder()
            val sb = StringBuilder()
            if (form != null) {
                for (entry in form.entries) {
                    val key = entry.key
                    if (param.isNotEmpty()) {
                        param.append("&")
                    }
                    param.append(key).append("=").append(entry.value)
                }
            }
            try {
                conn = URL(url).openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.doInput = true
                conn.readTimeout = readTimeout
                conn.connectTimeout = connectTimeout
                conn.useCaches = false
                conn.connect()
                os = OutputStreamWriter(conn.outputStream, "UTF-8")
                os.write(param.toString())
                rd = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8"))
                while (rd.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                respose = sb.toString()
            } catch (e: Exception) {
                logger.error("error post url:${url}${param}", e)
            } finally {
                try {
                    os?.close()
                    rd?.close()
                    conn?.connect()
                } catch (e: IOException) {
                    logger.error("error close conn", e)
                }
            }
            return respose
        }
    }
}
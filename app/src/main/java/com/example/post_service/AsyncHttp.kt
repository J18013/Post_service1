package com.example.post_service

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.sql.Time
import kotlin.properties.Delegates

class AsyncHttp : AsyncTask<Int,String,Boolean>{
    var urlConnection : HttpURLConnection by Delegates.notNull<HttpURLConnection>()
    var flg = false

    var time : Time by Delegates.notNull<Time>()//


    var name: String = ""
    var value: Double = 0.0

    constructor(name:String, value:Double, time: Time/**/){
        this.name = name
        this.value = value
        this.time = time
    }

    override fun doInBackground(vararg params: Int?): Boolean {
      // var urlinput : String = "http://10.206.0.71/upload/post.php"
        var urlinput : String = "http://10.206.0.71/Ice_Game/post.php"
        try {
            var url: URL = URL(urlinput)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "POST"
            urlConnection.doOutput = true

            var postDataSample: String = "name=" + name + "&text=" + value +time
            var out: OutputStream = urlConnection.outputStream
            out.write(postDataSample.toByteArray())
            out.flush()
            out.close()
            Log.d("test", postDataSample)
            urlConnection.inputStream
            flg = true

        }catch (e:MalformedURLException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
        return flg
    }



}
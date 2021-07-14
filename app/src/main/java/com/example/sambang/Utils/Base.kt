package com.example.sambang.Utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.example.sambang.Login.Data.ModelLogin
import org.jetbrains.anko.toast

open class Base : AppCompatActivity(){
    object TAGS{
        val KELUARGA = "keluarga"
        val NIKAKTIF = "nikaktif"
        val USER = "user"
        val PENERIMABANTUAN = "penerimaBantuan"
    }

    var user : ModelLogin? = null

    fun cekSesi(activity: Activity){
        val intent = intent.getSerializableExtra("user_data")

        if (intent == null){
            activity.toast("anda belum login").show()
            activity.finish()
        } else {
            user = intent as ModelLogin
        }

    }
}
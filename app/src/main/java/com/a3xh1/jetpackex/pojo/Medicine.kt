package com.a3xh1.jetpackex.pojo

import com.google.gson.annotations.SerializedName

/**
 * Author: GIndoc on 2018/10/12 下午2:54
 * FOR   : 中药
 */


data class Medicine(
    @SerializedName(value = "m_id", alternate = arrayOf("id"))
    var m_id: Int,
    @SerializedName(value = "medicine_name", alternate = arrayOf("name"))
    var medicine_name: String,
    var pics: String,
    var pinyin: String,
    var effect: String,
    var process: String,
    var issuer: String,
    var is_publish: Int,
    var is_delete: Int,
    var listorder: Int,
    var addtime: Int,
    var url: String
)

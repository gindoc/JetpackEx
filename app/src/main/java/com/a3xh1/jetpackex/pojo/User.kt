package com.a3xh1.jetpackex.pojo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Author: GIndoc on 2018/10/13 上午11:38
 * FOR   : 用户个人信息
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class User(
        var userid: Int,
        var avatar: String,
        var money: Double,
        var job: String,
        @SerializedName(value = "nickname", alternate = arrayOf("nick"))
        var nickname: String,
        var mobile: String,
        @SerializedName(value = "bind_tel", alternate = arrayOf("tel"))
        var bind_tel: String,
        var is_groom: Int,
        var is_act: Int,
        var is_msg: Int,
        var is_like: Int,
        var font: Int,
        var qrcode: String,
        var collect: Int,
        var bar: Int,
        var posts: Int,
        var agencys: Int,                 // 0申请代理 1审核中 2去缴费 3 代理开通成功
        var content: String,              // 推荐好友中的介绍
        var url: String,                  // 分享链接
        var access_token: String          // 用于判断是否账号是否在不同设备登录
) : Parcelable, Serializable
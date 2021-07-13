package com.example.sambang.domain.login.entity

data class LoginEntity(
    var userId : Int,
    var username : String,
    var email : String,
    var token: String
)
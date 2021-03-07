package com.heyoh.model.token

class Token {

    var authToken: String?=null
    var gradeId: Int? =null

    companion object{
        lateinit var instance: Token
    }
    init {
        instance = this
    }
}
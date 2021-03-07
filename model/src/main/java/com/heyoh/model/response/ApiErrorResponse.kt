package com.heyoh.model.response

data class ApiErrorResponse (val statusCode:Int, val message:String, val error:String)
package com.example.api.exception

class ConvertFailureException(param: String) : RuntimeException("Cant convert $param to API call")

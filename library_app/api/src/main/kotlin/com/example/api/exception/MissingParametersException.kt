package com.example.api.exception

class MissingParametersException(param: String) :
    RuntimeException("Missing parameters for param: $param")

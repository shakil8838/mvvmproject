package xyz.xandsoft.mvvmproject.utills

import java.io.IOException

class Exception(message: String) : IOException(message)

class NetworkException(message: String): IOException(message)
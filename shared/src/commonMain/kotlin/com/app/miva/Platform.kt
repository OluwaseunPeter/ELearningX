package com.app.miva

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
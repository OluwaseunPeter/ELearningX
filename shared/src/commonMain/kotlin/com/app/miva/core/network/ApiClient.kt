package com.app.miva.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val BASE_URL = "https://private-8af5e5-mobileappconsultant.apiary-mock.com"

fun getDefaultClient(): HttpClient {
    val client = HttpClient {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true })
        }

        install(DefaultRequest) {
            url(BASE_URL)
            header(HttpHeaders.ContentType, "application/json")
        }
    }
    return client
}
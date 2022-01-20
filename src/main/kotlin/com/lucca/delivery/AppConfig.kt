package com.lucca.delivery

data class AppConfig(
    val image: String,
    val deployedAt: String,
    val port: Int
) {
    val name = image.splitToSequence(':').elementAtOrElse(0) { "N/A" }
    val version = image.splitToSequence(':').elementAtOrElse(1) { "N/A" }
}
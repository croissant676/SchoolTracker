package dev.kason

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }
        val start = LocalTime
            .of(14, 35, 0)
            .atDate(LocalDate.of(2022, 3, 11))
        val end = LocalTime
            .of(7, 15, 0)
            .atDate(LocalDate.of(2022, 3, 21))
        val duration = Duration.between(start, end)
        routing {
            route("/") {
                get {
                    val currentDuration = Duration.between(start, LocalDateTime.now())
                    val percentage = currentDuration.seconds * 100.0 / duration.seconds
                    return@get call.respond(FreeMarkerContent("template.ftl", mapOf("value" to "$percentage%")))
                }
            }
        }
    }.start(wait = true)
}
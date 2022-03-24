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
    embeddedServer(Netty, port = (System.getenv("PORT") ?: "5000").toInt(), host = "0.0.0.0") {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }
        val start = LocalTime
            .of(7, 15, 0)
            .atDate(LocalDate.of(2022, 3, 21))
        val end = LocalTime
            .of(14, 35, 0)
            .atDate(LocalDate.of(2022, 5, 25))
        val duration = Duration.between(start, end)
        routing {
            route("/") {
                get {
                    val currentDuration = Duration.between(start, LocalDateTime.now())
                    val percentage = currentDuration.seconds * 100.0 / duration.seconds
                    call.respond(
                        FreeMarkerContent(
                            "template.ftl", mapOf(
                                "value" to "$percentage%",
                                "hours" to (currentDuration.seconds / 3600.0).toString(),
                                "total" to (duration.seconds / 3600.0).toString()
                            )
                        )
                    )
                }
            }
        }
    }.start(wait = true)
}
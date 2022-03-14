package dev.kason

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.css.CssBuilder
import kotlinx.css.body
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.title

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val start = LocalTime
            .of(2, 35, 0)
            .atDate(LocalDate.of(2022, 3, 11))
        val end = LocalTime
            .of(7, 15, 0)
            .atDate(LocalDate.of(2022, 3, 21))
        val duration = Duration.between(start, end)
        routing {
            route("/") {
                get {
                    val currentDuration = Duration.between(start, Instant.now())
                    val percentage = currentDuration.seconds.toDouble() / duration.seconds.toDouble()
                    return@get call.respondHtml {
                        head {
                            title { +"Spring break % counter" }
                        }
                        body {

                        }
                    }
                }
                get("/styles.css") {
                    return@get call.respondCss {
                        body {

                        }
                    }
                }
            }
        }
    }.start(wait = true)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = 8080

    val server = embeddedServer(Netty, port, module = Application::mainModule)

    server.start()
}

fun Application.mainModule() {
    install(ContentNegotiation) {
        jackson{
            enable(SerializationFeature.INDENT_OUTPUT)

        }
        routing {
            trace {
                application.log.debug(it.buildText())
            }
            get {
                context.respond(mapOf("Welcome" to "the Cat Hostel"))
            }
            get("/{name}") {
                val name = context.parameters["name"]
                context.respond(mapOf("Cat name:" to name))
            }
        }
    }

}
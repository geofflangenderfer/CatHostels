import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.Application
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    @Test
    fun emptyPath() {
        withTestApplication(Application::mainModule) {
            val call = handleRequest(HttpMethod.Get, "")

            assertEquals(HttpStatusCode.OK, call.response.status())
        }

    }
    @Test
    fun validValue() {
        withTestApplication(Application::mainModule) {
            val call = handleRequest(HttpMethod.Get, "/cunty")

            val om = ObjectMapper()
            assertEquals("""
               {
                 "Cat name:" : "cunty"
               }
            """.asJson(), call.response.content?.asJson())
        }
    }
}
private fun String.asJson() = ObjectMapper().readTree(this)
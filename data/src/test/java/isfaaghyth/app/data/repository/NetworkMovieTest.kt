package isfaaghyth.app.data.repository

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Experimental purpose
 */
class NetworkMovieTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var success: MockResponse
    private lateinit var error: MockResponse

    @Before fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.url("/")

        success = MockResponse()
            .setBody(successJson)
            .setResponseCode(200)

        error = MockResponse()
            .setBody(errorJson)
            .setResponseCode(500)
    }

    @Test fun `test success mock web server`() {
        mockWebServer.enqueue(success)
        assertEquals("HTTP/1.1 200 OK", success.status)
    }

    @Test fun `test error mock web server`() {
        mockWebServer.enqueue(error)
        assertEquals("HTTP/1.1 500 Server Error", error.status)
    }

    @After fun tearDown() {
        mockWebServer.shutdown()
    }

}
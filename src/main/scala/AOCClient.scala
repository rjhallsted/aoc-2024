import zio._
import zio.http._

object AOCClient {
    type Res[U] = RIO[Client & Scope, U]
    
    def getInput(day: Int): Res[String] = {
        val url = s"https://adventofcode.com/2023/day/$day/input"

        val cookie = sys.env.get("AOC_SESSION_KEY").map(sessionKey => Cookie.Request("session", sessionKey))

        for {
            sessionCookie <- ZIO.getOrFail(cookie)
            cookieHeader <- ZIO.succeed(Header.Cookie(NonEmptyChunk(sessionCookie)))
            req <- ZIO.succeed(Request.get(url).addHeader(cookieHeader))
            res <- ZClient.request(req)
            body <- res.body.asString
        } yield body
    }
}
package start;

import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
public class MyHttpApp extends HttpApp {
	@Override
	protected Route routes() {
		// This handler generates responses to `/hello?name=XXX` requests
		Route theRoute = parameterOptional("name", optName -> {
			String name = optName.orElse("Mister X");
			return complete("Hello " + name + "!");
		});

		return route(
				// here the complete behavior for this server is defined
				// only handle GET requests
				get(() -> route(
						// matches the empty path
						pathSingleSlash(() ->
								// return a constant string with a certain content type
								complete(HttpEntities.create(ContentTypes.TEXT_HTML_UTF8, "<html><body>Hello world!</body></html>"))
						),
						path("ping", () ->
								// return a simple `text/plain` response
								complete("PONG!")
						),
						path("abcd", () -> {
									return extractUri(uri -> {
												return complete(uri + "");
											}
									);
								}
						),
						path("hello", () -> {
									// uses the route defined above
									return theRoute;
								}
						)
				)),
				post(() -> route(
						// matches the empty path
						pathSingleSlash(() ->
								// return a constant string with a certain content type
								complete(HttpEntities.create(ContentTypes.TEXT_HTML_UTF8, "<html><body>Hello world!</body></html>"))
						),
						path("ping", () ->
								// return a simple `text/plain` response
								complete("PONG!")
						),
						path("hello", () ->
								// uses the route defined above
								theRoute
						)
				))
		);
	}
}


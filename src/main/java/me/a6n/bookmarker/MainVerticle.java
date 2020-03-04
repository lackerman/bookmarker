package me.a6n.bookmarker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import me.a6n.bookmarker.utils.Choice;

public class MainVerticle extends AbstractVerticle {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MainVerticle());
	}

	@Override
	public void start(Promise<Void> promise) {
		// Setup bookmarks
		var handler = DaggerDI_BookmarkHandlerFactory.builder().build().handler();
		var bookmarks = Router.router(vertx);
		bookmarks.get("/")
			.produces("application/json")
			.consumes("application/json")
			.handler(handler::all);
		bookmarks.get("/:id")
			.produces("application/json")
			.consumes("application/json")
			.handler(handler::bookmark);
		bookmarks.post("/:id")
			.produces("application/json")
			.consumes("application/json")
			.handler(handler::add);

		// API Sub router
		var apiV1 = Router.router(vertx);
		apiV1.mountSubRouter("/bookmarks", bookmarks);

		var mainRouter = Router.router(vertx);
		mainRouter.mountSubRouter("/api/v1", apiV1);

		// Serve static resources from the /assets directory
		mainRouter.route("/scripts/*")
			.handler(StaticHandler.create("scripts"));
		mainRouter.route("/styles/*")
			.handler(StaticHandler.create("styles"));
		mainRouter.route("/*")
			.handler(StaticHandler.create("."));

		vertx.createHttpServer()
			.requestHandler(mainRouter)
			.listen(
				config().getInteger("http.port", 8080),
				result -> Choice.of(result.succeeded())
					.ifTrue(promise::complete)
					.ifFalse(() -> promise.fail(result.cause()))
			);
	}
}

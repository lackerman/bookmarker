package me.a6n.bookmarker.bookmarks;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

public class BookmarkHandler {
	private final BookmarkService service;

	@Inject
	public BookmarkHandler(BookmarkService service) {
		this.service = service;
	}

	public void bookmark(RoutingContext ctx) {
		String id = ctx.pathParam("id");
		service.findById(id).ifPresentOrElse(
			bookmark ->
				ctx.response()
					.putHeader("content-type", "application/json")
					.end(Json.encode(bookmark)),
			() ->
				ctx.response()
					.setStatusCode(HttpResponseStatus.NOT_FOUND.code())
					.end()
		);
	}

	public void add(RoutingContext ctx) {
		final Bookmark bookmark = Json.decodeValue(ctx.getBodyAsString(), Bookmark.class);
		service.add(bookmark);
		ctx.response()
			.putHeader("content-type", "application/json")
			.setStatusCode(HttpResponseStatus.CREATED.code())
			.end();
	}

	public void all(RoutingContext ctx) {
		ctx.response()
			.putHeader("content-type", "application/json")
			.end(Json.encode(service.getAll()));
	}
}

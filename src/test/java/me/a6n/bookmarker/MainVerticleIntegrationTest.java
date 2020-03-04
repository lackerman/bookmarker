package me.a6n.bookmarker;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@ExtendWith(VertxExtension.class)
class MainVerticleIntegrationTest {

	private int port = new Random().nextInt(1000) + 30000;

	@BeforeEach
	void before(Vertx vertx, VertxTestContext testContext) {
		var options = new DeploymentOptions()
			.setConfig(new JsonObject().put("http.port", port));
		vertx.deployVerticle(new MainVerticle(), options, testContext.completing());
	}

	@AfterEach
	void after(Vertx vertx, VertxTestContext context) {
		vertx.close();
	}

	@Test
	void test(Vertx vertx, VertxTestContext testContext) {
		WebClient webClient = WebClient.create(vertx);
		webClient.get(port, "localhost", "/api/v1/bookmarks")
			.as(BodyCodec.string())
			.send(testContext.succeeding(resp -> {
				testContext.verify(() -> {
					assertThat(resp.statusCode(), is(200));
					assertThat(resp.getHeader("content-type"), is("json"));
					assertThat(resp.body(), containsString("Yo!"));
					testContext.completeNow();
				});
			}));
	}
}
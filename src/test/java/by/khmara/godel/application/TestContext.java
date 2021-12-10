package by.khmara.godel.application;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.Qualifier;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.type.Argument;
import io.micronaut.runtime.server.EmbeddedServer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

import static java.lang.String.format;

/**
 * {@see docs https://objectcomputing.com/files/9815/9259/7089/slide_deck_Micronaut_Testing_Best_Practices_webinar.pdf}
 */
public class TestContext {

	private static final PostgreSQLContainer<?> database;
	private static final ApplicationContext context;

	static {
		database = new PostgreSQLContainer<>("postgres:12");
		database.start();
		context =
			ApplicationContext
				.run(
					EmbeddedServer.class,
					Map.of(
						"flyway.datasources.default.url",
						database.getJdbcUrl(),
						"flyway.datasources.default.user",
						database.getUsername(),
						"flyway.datasources.default.password",
						database.getPassword(),

						"r2dbc.datasources.default.url",
						format("r2dbc:postgresql://%s:%d/%s",
							database.getContainerIpAddress(),
							database.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
							database.getDatabaseName()
						),
						"r2dbc.datasources.default.username",
						database.getUsername(),
						"r2dbc.datasources.default.password",
						database.getPassword()
					)
				)
				.getApplicationContext();
	}

	public static TestClient getClient() {
		return context.getBean(TestClient.class);
	}

	public static <T> T getBean(@NonNull Class<T> beanType) {
		return context.getBean(beanType);
	}

	public static <T> T getBean(@NonNull Argument<T> beanType) {
		return context.getBean(beanType);
	}

	public static <T> T getBean(
		@NonNull Class<T> beanType,
		@Nullable Qualifier<T> qualifier
	) {
		return context.getBean(beanType, qualifier);
	}

	public static <T> T getBean(
		@NonNull Argument<T> beanType,
		@Nullable Qualifier<T> qualifier
	) {
		return context.getBean(beanType, qualifier);
	}
}

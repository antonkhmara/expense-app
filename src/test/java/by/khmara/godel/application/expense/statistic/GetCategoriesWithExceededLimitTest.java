package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class GetCategoriesWithExceededLimitTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturn1CategoryWithExceededLimit() {
		var categoriesWithExceededLimit = client.getCategoriesWithExceededLimit();

		assertThat(categoriesWithExceededLimit).hasSize(1);
		assertThat(categoriesWithExceededLimit.containsKey("medications"))
			.isTrue();
	}
}

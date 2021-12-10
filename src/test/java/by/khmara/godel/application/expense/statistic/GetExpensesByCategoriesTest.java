package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class GetExpensesByCategoriesTest {
	TestClient client = TestContext.getClient();

	@Test
	void should() {
		var expenses = client.getExpensesByCategories("education");

		assertThat(expenses).hasSize(3);
		assertThat(expenses.get(0)).isInstanceOf(ExpenseByCategoryResponse.class);
	}
}

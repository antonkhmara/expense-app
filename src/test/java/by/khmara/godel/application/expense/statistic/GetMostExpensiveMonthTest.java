package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class GetMostExpensiveMonthTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturnExpensesByMonth() {
		var expensesByMonthResponses = client.getMostExpensiveMonth();

		assertThat(expensesByMonthResponses).hasSize(2);
		assertThat(expensesByMonthResponses.get(0)).isInstanceOf(ExpensesByMonthResponse.class);
	}
}

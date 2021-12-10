package by.khmara.godel.application.expense.query;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static by.khmara.godel.application.TestUtils.expenseQueryRequest;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class GetExpensesByQueryTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturn17Expenses() {
		var expenses = client.getExpenses(new ExpenseQueryRequest());

		assertThat(expenses).hasSize(17);
	}

	@Test
	void shouldReturn3ExpenseFromFoodCategory() {
		var expenses = client.getExpenses(expenseQueryRequest("food"));

		assertThat(expenses).hasSize(3);
	}

	@Test
	void shouldReturn16ExpansesFromDateInterval() {
		var expenses = client.getExpenses(expenseQueryRequest(LocalDateTime.now().minusDays(1), LocalDateTime.now()));

		assertThat(expenses).hasSize(16);
	}

	@Test
	void shouldReturn16ExpansesToDate() {
		var expenses = client.getExpenses(expenseQueryRequest(null, LocalDateTime.now()));

		assertThat(expenses).hasSize(17);
	}

	@Test
	void shouldReturn16ExpansesFromDate() {
		var expenses = client.getExpenses(expenseQueryRequest(LocalDateTime.now().minusDays(1), null));

		assertThat(expenses).hasSize(16);
	}

	@Test
	void shouldReturn2PageWith8Expanses() {
		var request = new ExpenseQueryRequest();
		request.setPageSize(8);
		request.setPage(2);
		var expenses = client.getExpenses(request);

		assertThat(expenses).hasSize(8);
	}
}

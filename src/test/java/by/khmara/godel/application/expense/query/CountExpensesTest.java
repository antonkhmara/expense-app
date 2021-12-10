package by.khmara.godel.application.expense.query;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class CountExpensesTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturn16ExpensesWithDefaultRequest() {
		var x = client.countExpenses(new ExpenseQueryRequest());

		assertThat(x.total()).isEqualTo(16);
	}

//	TODO: add more tests with different query's requests
}

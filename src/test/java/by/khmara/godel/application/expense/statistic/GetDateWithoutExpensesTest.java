package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class GetDateWithoutExpensesTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturnDateWithoutExpenses() {
		var request = new DatesIntervalRequest(LocalDateTime.now().minusDays(100), LocalDateTime.now());
		var dateWithoutExpenses = client.getDayWithoutExpenses(request);

		assertThat(dateWithoutExpenses).isInstanceOf(DateWithoutExpensesResponse.class);
	}
}

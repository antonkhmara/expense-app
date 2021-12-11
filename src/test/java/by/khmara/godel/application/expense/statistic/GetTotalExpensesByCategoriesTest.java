package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class GetTotalExpensesByCategoriesTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturnTotalExpensesByCategories() {
		var totalExpenseResponses = client.getTotalExpenseByCategories();

		assertThat(totalExpenseResponses).hasSize(6);
		assertThat(totalExpenseResponses.get(0)).isInstanceOf(TotalExpenseResponse.class);
	}

}

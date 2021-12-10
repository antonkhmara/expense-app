package by.khmara.godel.application.expense.statistic;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Testcontainers
public class GetTotalAmountTest {
	TestClient client = TestContext.getClient();

	@Test
	void shouldReturnTotalAmountOfAllExpenses() {
		var totalAmount = client.getTotalAmount();

		assertThat(totalAmount).isInstanceOf(Integer.class);
		assertThat(totalAmount).isEqualTo(1001224);
	}
}

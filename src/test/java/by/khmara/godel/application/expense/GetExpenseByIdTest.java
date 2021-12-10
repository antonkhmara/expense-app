package by.khmara.godel.application.expense;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static by.khmara.godel.application.TestUtils.randomExpense;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class GetExpenseByIdTest {

	@Inject
	TestClient client;

	@Test
	void should() {
		var expense = randomExpense(client);
		var x = client.getExpanse(expense.id());
		assertThat(x).isInstanceOf(ExpenseResponse.class);
	}
}

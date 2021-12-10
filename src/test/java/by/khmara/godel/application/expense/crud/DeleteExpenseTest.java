package by.khmara.godel.application.expense.crud;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testcontainers.junit.jupiter.Testcontainers;

import static by.khmara.godel.application.TestUtils.randomExpense;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
public class DeleteExpenseTest {
	ExpenseResponse expense;
	TestClient client = TestContext.getClient();

	@BeforeAll
	void prepareData() {
		expense = randomExpense(client);
	}

	@Test
	void shouldDeleteExpense() {
		var responseBeforeDelete = client.getExpanse(expense.id());
		client.deleteExpense(expense.id());
		var responseAfterDelete = client.getExpanse(expense.id());

		assertThat(responseBeforeDelete).isInstanceOf(ExpenseResponse.class);
		assertThat(responseAfterDelete).isNull();
	}

	@Test
	void shouldThrownExceptionIfIdIsNull() {
		assertThatThrownBy(() -> client.deleteExpense(null))
			.isInstanceOf(IllegalArgumentException.class);
	}
}

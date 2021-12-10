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
import static by.khmara.godel.application.TestUtils.randomId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
public class GetExpenseByIdTest {
	ExpenseResponse expense;
	TestClient client = TestContext.getClient();

	@BeforeAll
	void prepareData() {
		expense = randomExpense(client);
	}

	@Test
	void shouldReturnExpenseResponse() {
		var response = client.getExpanse(expense.id());

		assertThat(response).isInstanceOf(ExpenseResponse.class);
		assertThat(response.id()).isEqualTo(expense.id());
		assertThat(response.description()).isEqualTo(expense.description());
		assertThat(response.category().id()).isEqualTo(expense.category().id());
	}

	@Test
	void shouldReturnNullIfIdNotExist() {
		var response = client.getExpanse(randomId());

		assertThat(response).isNull();
	}

	@Test
	void shouldThrownExceptionIfIdIsNull() {
		assertThatThrownBy(() -> client.getExpanse(null))
			.isInstanceOf(IllegalArgumentException.class);
	}
}

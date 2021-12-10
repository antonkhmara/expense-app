package by.khmara.godel.application.expense;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static by.khmara.godel.application.TestUtils.createCategory;
import static by.khmara.godel.application.TestUtils.expenseUpdateRequest;
import static by.khmara.godel.application.TestUtils.randomAmount;
import static by.khmara.godel.application.TestUtils.randomExpense;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MicronautTest
@TestInstance(Lifecycle.PER_CLASS)
public class UpdateExpenseTest {
	ExpenseResponse expense;

	@Inject
	TestClient client;

	@BeforeAll
	void prepareData() {
		expense = randomExpense(client);
	}

	@Test
	void shouldReturnExpenseResponse() {
		var updated = client.updateExpense(expense.id(), new ExpenseUpdateRequest(
			"new description", LocalDateTime.now(), createCategory(), randomAmount()));

		assertThat(updated).isInstanceOf(ExpenseResponse.class);
	}

	@Test
	void shouldReturnEqualId() {
		var updated = client.updateExpense(expense.id(), expenseUpdateRequest());

		assertThat(updated.id()).isEqualTo(expense.id());
	}

	@Test
	void shouldReturnUpdatedDescription() {
		var updated = client.updateExpense(expense.id(), expenseUpdateRequest("updated description"));

		assertThat(updated.description()).isEqualTo("updated description");
	}

	@Test
	void shouldThrownViolationExceptionIfDescriptionIsBlank() {
		assertThatThrownBy(() -> client.updateExpense(expense.id(), expenseUpdateRequest("")))
			.isInstanceOf(ConstraintViolationException.class);
	}
}

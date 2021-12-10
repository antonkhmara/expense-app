package by.khmara.godel.application.expense;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestUtils;
import by.khmara.godel.application.expense.models.Category;
import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.UUID;

import static by.khmara.godel.application.TestUtils.createCategory;
import static by.khmara.godel.application.TestUtils.randomAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@MicronautTest
public class AddExpenseTest {
	@Inject
	TestClient client;

	@Test
	void shouldCreateExpense() {
		var response = TestUtils.randomExpense(client);

		assertThat(response.id()).isInstanceOf(UUID.class);
		assertThat(response.description()).isInstanceOf(String.class);
		assertThat(response.category()).isInstanceOf(Category.class);
		assertThat(response.createdAt()).isInstanceOf(LocalDateTime.class);
		assertThat(response.amount()).isInstanceOf(Double.class);
	}

	@Test
	void shouldReturnExpenseResponse() {
		var response = TestUtils.randomExpense(client);

		assertThat(response).isInstanceOf(ExpenseResponse.class);
	}

	@Test
	void shouldThrowViolationExceptionIfDescriptionIsBlank() {
		var request = new ExpenseCreateRequest("", createCategory(), randomAmount());

		assertThatThrownBy(() -> client.createExpanse(request))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void shouldThrowViolationExceptionIfCategoryIsNull() {
		var request = new ExpenseCreateRequest("", null, randomAmount());

		assertThatThrownBy(() -> client.createExpanse(request))
			.isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void shouldThrowViolationExceptionIfAmountIsNull() {
		var request = new ExpenseCreateRequest("", createCategory(), null);

		assertThatThrownBy(() -> client.createExpanse(request))
			.isInstanceOf(ConstraintViolationException.class);
	}
}

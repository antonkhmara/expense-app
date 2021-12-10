package by.khmara.godel.application.expense.crud;

import by.khmara.godel.application.TestClient;
import by.khmara.godel.application.TestContext;
import by.khmara.godel.application.TestUtils;
import by.khmara.godel.application.expense.models.Category;
import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.UUID;

import static by.khmara.godel.application.TestUtils.createCategory;
import static by.khmara.godel.application.TestUtils.randomAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
public class AddExpenseTest {
	ExpenseResponse response;
	TestClient client = TestContext.getClient();

	@BeforeAll
	void prepareData() {
		response = TestUtils.randomExpense(client);
	}

	@Test
	void shouldCreateExpense() {
		assertThat(response.id()).isInstanceOf(UUID.class);
		assertThat(response.description()).isInstanceOf(String.class);
		assertThat(response.category()).isInstanceOf(Category.class);
		assertThat(response.createdAt()).isInstanceOf(LocalDateTime.class);
		assertThat(response.amount()).isInstanceOf(Double.class);
	}

	@Test
	void shouldReturnExpenseResponse() {
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

	@AfterAll
	void deleteData() {
		client.deleteExpense(response.id());
	}
}

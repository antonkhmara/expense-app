package by.khmara.godel.application;

import by.khmara.godel.application.expense.models.Category;
import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public final class TestUtils {
	private static final AtomicLong counter = new AtomicLong();

	public static UUID randomId() {
		return UUID.randomUUID();
	}

	public static Double randomAmount() {
		var random = new Random();
		return random.nextDouble(100);
	}

	public static Category createCategory() {
		return new Category(UUID.fromString("82ff2531-4fa7-4d62-a81b-505eed792b1a"), "food");
	}

	public static ExpenseCreateRequest expenseCreateRequest() {
		return new ExpenseCreateRequest(
			"description#" + counter, createCategory(), randomAmount());
	}

	public static ExpenseUpdateRequest expenseUpdateRequest() {
		return new ExpenseUpdateRequest("description#" + counter, LocalDateTime.now(), createCategory(), randomAmount());
	}

	public static ExpenseUpdateRequest expenseUpdateRequest(String description) {
		return new ExpenseUpdateRequest(description, LocalDateTime.now(), createCategory(), randomAmount());
	}

	public static ExpenseResponse randomExpense(TestClient client) {
		return client.createExpanse(expenseCreateRequest());
	}
}

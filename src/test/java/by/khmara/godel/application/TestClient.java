package by.khmara.godel.application;

import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.CountResponse;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Client("/")
public interface TestClient {

	@Get("/expense/v1/expanses/{expenseId}")
	ExpenseResponse getExpanse(@NonNull UUID expenseId);

	@Post("/expense/v1/expanses")
	ExpenseResponse createExpanse(@Valid @Body ExpenseCreateRequest req);

	@Put("/expense/v1/{expenseId}")
	ExpenseResponse updateExpense(UUID expenseId, @Valid @Body ExpenseUpdateRequest req);

	@Delete("/expense/v1/{expanseId}")
	Long deleteExpense(UUID expanseId);

	@Get("/expenses/v1/search{?req*}")
	List<ExpenseResponse> getExpenses(@Valid ExpenseQueryRequest req);

	@Get("/expenses/v1/count{?req*}")
	CountResponse countExpenses(@Valid ExpenseQueryRequest req);
}

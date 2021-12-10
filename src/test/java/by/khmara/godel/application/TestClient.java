package by.khmara.godel.application;

import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.CountResponse;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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

	@Get("/expense/v1/statistics/totalAmount")
	Integer getTotalAmount();

	@Get("/expense/v1/statistics/totalExpenseByCategories")
	List<TotalExpenseResponse> getTotalExpenseByCategories();

	@Get("/expense/v1/statistics/theMostExpensiveMonth")
	List<ExpensesByMonthResponse> getMostExpensiveMonth();

	@Get("/expense/v1/statistics/categoriesWithExceededLimit")
	Map<String, Double> getCategoriesWithExceededLimit();

	@Get("/expense/v1/statistics/expensesByCategories/{categoryName}")
	List<ExpenseByCategoryResponse> getExpensesByCategories(String categoryName);

	@Get("/expense/v1/statistics/dayWithOutExpenses{?req*}")
	DateWithoutExpensesResponse getDayWithoutExpenses(DatesIntervalRequest req);
}

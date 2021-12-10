package by.khmara.godel.contract.expense;

import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

public interface ExpenseStatisticContract {
	@Get("/expense/v1/statistics/totalAmount")
	Mono<Integer> getTotalAmount();

	@Get("/expense/v1/statistics/totalExpenseByCategories")
	Flux<TotalExpenseResponse> getTotalExpenseByCategories();

	@Get("/expense/v1/statistics/theMostExpensiveMonth")
	Flux<ExpensesByMonthResponse> getMostExpensiveMonth();

	@Get("/expense/v1/statistics/categoriesWithExceededLimit")
	Mono<Map<String, Double>> getCategoriesWithExceededLimit();

	@Get("/expense/v1/statistics/expensesByCategories/{categoryName}")
	Flux<ExpenseByCategoryResponse> getExpensesByCategories(String categoryName);

	@Get("/expense/v1/statistics/dayWithOutExpenses{?req*}")
	Mono<DateWithoutExpensesResponse> getDayWithoutExpenses(@Valid DatesIntervalRequest req);
}

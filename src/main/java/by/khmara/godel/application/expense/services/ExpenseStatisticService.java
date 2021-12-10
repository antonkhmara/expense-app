package by.khmara.godel.application.expense.services;

import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ExpenseStatisticService {
	Mono<Integer> totalExpenses();

	Flux<TotalExpenseResponse> totalExpensesByCategories();

	Flux<ExpensesByMonthResponse> theMostExpensiveMonth();

	Mono<Map<String, Double>> categoriesWithExceededLimit();

	Flux<ExpenseByCategoryResponse> listExpensesByCategory(String categoryName);

	Mono<DateWithoutExpensesResponse> searchDayWithoutExpansesFromInterval(DatesIntervalRequest req);
}

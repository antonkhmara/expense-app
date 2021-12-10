package by.khmara.godel.application.expense.controllers;

import by.khmara.godel.application.expense.services.ExpenseStatisticService;
import by.khmara.godel.contract.expense.ExpenseStatisticContract;
import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Validated
@Controller
@RequiredArgsConstructor
public class ExpenseStatisticController implements ExpenseStatisticContract {
	private final ExpenseStatisticService expenseStatisticService;

	@Override
	public Mono<Integer> getTotalAmount() {
		return expenseStatisticService.totalExpenses();
	}

	@Override
	public Flux<TotalExpenseResponse> getTotalExpenseByCategories() {
		return expenseStatisticService.totalExpensesByCategories();
	}

	@Override
	public Flux<ExpensesByMonthResponse> getMostExpensiveMonth() {
		return expenseStatisticService.theMostExpensiveMonth();
	}

	@Override
	public Mono<Map<String, Double>> getCategoriesWithExceededLimit() {
		return expenseStatisticService.categoriesWithExceededLimit();
	}

	@Override
	public Flux<ExpenseByCategoryResponse> getExpensesByCategories(String categoryName) {
		return expenseStatisticService.listExpensesByCategory(categoryName);
	}

	@Override
	public Mono<DateWithoutExpensesResponse> getDayWithoutExpenses(DatesIntervalRequest req) {
		return expenseStatisticService.searchDayWithoutExpansesFromInterval(req);
	}
}

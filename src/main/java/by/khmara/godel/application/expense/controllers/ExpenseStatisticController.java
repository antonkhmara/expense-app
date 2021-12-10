package by.khmara.godel.application.expense.controllers;

import by.khmara.godel.application.expense.services.ExpenseStatisticService;
import by.khmara.godel.contract.expense.response.statistics.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.TotalExpenseResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequiredArgsConstructor
public class ExpenseStatisticController {
	private final ExpenseStatisticService expenseStatisticService;

	@Get("/expense/v1/statistics/totalAmount")
	public Mono<Integer> getTotalAmount() {
		return expenseStatisticService.totalExpenses();
	}

	@Get("/expense/v1/statistics/totalExpenseByCategories")
	public Flux<TotalExpenseResponse> getTotalExpenseByCategories() {
		return expenseStatisticService.totalExpensesByCategories();
	}

	@Get("/expense/v1/statistics/theMostExpensiveMonth")
	public Flux<ExpensesByMonthResponse> getMostExpensiveMonth() {
		return expenseStatisticService.theMostExpensiveMonth();
	}

	@Get("/expense/v1/statistics/categoriesWithExceededLimit")
	public Mono<Map<String, Double>> getCategoriesWithExceededLimit() {
		return expenseStatisticService.categoriesWithExceededLimit();
	}

	@Get("/expense/v1/statistics/expensesByCategories/{categoryName}")
	public Flux<String> getExpensesByCategories(String categoryName) {
		return expenseStatisticService.listExpensesByCategory(categoryName);
	}

	@Get("/expense/v1/statistics/dayWithOutExpenses/{dates}")
	public Mono<LocalDateTime> getDayWithoutExpenses(List<LocalDateTime> dates) {
		return expenseStatisticService.searchDayWithoutExpansesFromInterval(dates);
	}

}

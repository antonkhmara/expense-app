package by.khmara.godel.application.expense.services;

import by.khmara.godel.contract.expense.response.statistics.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.TotalExpenseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ExpenseStatisticService {
	Mono<Integer> totalExpenses();

	Flux<TotalExpenseResponse> totalExpensesByCategories();

	Flux<ExpensesByMonthResponse> theMostExpensiveMonth();

	Mono<Map<String, Double>> categoriesWithExceededLimit();

	Flux<String> listExpensesByCategory(String categoryName);

	Mono<LocalDateTime> searchDayWithoutExpansesFromInterval(List<LocalDateTime> dates);
}

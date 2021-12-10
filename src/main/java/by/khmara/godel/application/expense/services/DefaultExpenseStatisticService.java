package by.khmara.godel.application.expense.services;

import by.khmara.godel.application.expense.services.utils.ConvertorStatisticUtil;
import by.khmara.godel.contract.expense.response.statistics.request.DatesIntervalRequest;
import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import io.micronaut.data.r2dbc.operations.R2dbcOperations;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Map;

import static by.khmara.godel.application.expense.services.queries.constants.StatisticQueryConstants.LIST_OF_EXPENSES_BY_CATEGORY;
import static by.khmara.godel.application.expense.services.queries.constants.StatisticQueryConstants.MOST_EXPENSIVE_MONTH;
import static by.khmara.godel.application.expense.services.queries.constants.StatisticQueryConstants.SEARCH_DAY_WITHOUT_EXPENSES;
import static by.khmara.godel.application.expense.services.queries.constants.StatisticQueryConstants.TOTAL_SUM_OF_AMOUNT;
import static by.khmara.godel.application.expense.services.queries.constants.StatisticQueryConstants.TOTAL_SUM_OF_AMOUNT_BY_CATEGORIES;
import static java.lang.String.format;

@Singleton
@RequiredArgsConstructor
public class DefaultExpenseStatisticService implements ExpenseStatisticService {
	private final R2dbcOperations r2dbcOperations;

	@Override
	@Transactional
	public Mono<Integer> totalExpenses() {
		return Mono.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(TOTAL_SUM_OF_AMOUNT)
				.execute()))
			.map(result -> result.map(ConvertorStatisticUtil::convertToTotalSum))
			.flatMap(Mono::from);
	}

	@Override
	@Transactional
	public Flux<TotalExpenseResponse> totalExpensesByCategories() {
		return Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(TOTAL_SUM_OF_AMOUNT_BY_CATEGORIES)
				.execute()))
			.flatMap(result -> result.map(ConvertorStatisticUtil::convertToTotalExpenseResponse));
	}

	@Override
	@Transactional
	public Flux<ExpensesByMonthResponse> theMostExpensiveMonth() {
		return Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(MOST_EXPENSIVE_MONTH)
				.execute()))
			.flatMap(result -> result.map(ConvertorStatisticUtil::convertToMostExpensiveMonthResponse));
	}

	@Override
	@Transactional
	public Mono<Map<String, Double>> categoriesWithExceededLimit() {
//		var expensesByPresentMonth = Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
//				.createStatement(TOTAL_EXPENSES_BY_PRESENT_MONTH)
//				.execute()))
//			.flatMap(result -> result.map(ConvertorStatisticUtil::convertToExceededLimitByCategoriesResponse))
//			.collectMap(ExceededLimitByCategoriesResponse::categoryName, ExceededLimitByCategoriesResponse::amount).blockOptional().get();
//
//		var expensesByPreviousMonth = Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
//				.createStatement(TOTAL_EXPENSES_BY_PREVIOUS_MONTH)
//				.execute()))
//			.flatMap(result -> result.map(ConvertorStatisticUtil::convertToExceededLimitByCategoriesResponse))
//			.collectMap(ExceededLimitByCategoriesResponse::categoryName, ExceededLimitByCategoriesResponse::amount).blockOptional().get();
//
//		var keySet = expensesByPresentMonth.keySet().stream().toList();
//		Map<String, Double> exceededExpenses = new HashMap<>();
//		for (int i = 0; i < keySet.size(); i++) {
//			if (expensesByPreviousMonth.containsKey(keySet.get(i))) {
//				if (expensesByPresentMonth.get(keySet.get(i)) < expensesByPreviousMonth.get(keySet.get(i))) {
//					exceededExpenses.put(keySet.get(i), expensesByPreviousMonth.get(keySet.get(i)));
//				}
//			}
//		}
//		return Mono.just(exceededExpenses);
		return null;
	}

	@Override
	@Transactional
	public Flux<ExpenseByCategoryResponse> listExpensesByCategory(String categoryName) {
		return Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(format(LIST_OF_EXPENSES_BY_CATEGORY, categoryName))
				.execute()))
			.flatMap(result -> result.map(ConvertorStatisticUtil::convertToExpenseByCategoryResponse));
	}

	@Override
	@Transactional
	public Mono<DateWithoutExpensesResponse> searchDayWithoutExpansesFromInterval(DatesIntervalRequest req) {
		return Mono.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(format(SEARCH_DAY_WITHOUT_EXPENSES, req.fromDate(), req.toDate()))
				.execute()))
			.map(result -> result.map(ConvertorStatisticUtil::convertToDateWithoutExpensesResponse))
			.flatMap(Mono::from);
	}
}

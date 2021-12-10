package by.khmara.godel.application.expense.services;

import by.khmara.godel.application.expense.models.Expense;
import by.khmara.godel.application.expense.models.ExpenseRepository;
import by.khmara.godel.application.expense.services.queries.QueryBuilder;
import by.khmara.godel.application.expense.services.utils.ConvertorUtil;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import io.micronaut.data.r2dbc.operations.R2dbcOperations;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class DefaultExpenseService implements ExpenseService {
	private final QueryBuilder queryBuilder;
	private final R2dbcOperations r2dbcOperations;
	private final ExpenseRepository expenseRepository;

	@Override
	@Transactional
	public Flux<Expense> getExpenses(ExpenseQueryRequest req) {
		return Flux.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(queryBuilder.buildSelectQuery(req))
				.execute()))
			.flatMap(result -> result.map(ConvertorUtil::convertToExpense));
	}

	@Override
	@Transactional
	public Mono<Integer> countExpenses(ExpenseQueryRequest req) {
		return Mono.from(r2dbcOperations.withTransaction(connection -> connection.getConnection()
				.createStatement(queryBuilder.buildCountQuery(req))
				.execute())).map(result -> result.map(ConvertorUtil::convertToCount))
			.flatMap(Mono::from);
	}

	@Override
	public Mono<Expense> getExpense(UUID expenseId) {
		return expenseRepository.findById(expenseId);
	}

	@Override
	public Mono<Expense> createExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Override
	public Mono<Expense> updateExpense(UUID expenseId, ExpenseUpdateRequest req) {
		var expense = new Expense(expenseId, req.description(), req.createdAt(), req.category(), req.amount());
		return expenseRepository.update(expense);
	}

	@Override
	public Mono<Void> deleteExpense(UUID expenseId) {
		return expenseRepository.delete(expenseId);
	}
}



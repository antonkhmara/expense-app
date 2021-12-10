package by.khmara.godel.application.expense.services;

import by.khmara.godel.application.expense.models.Expense;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ExpenseService {
	Flux<Expense> getExpenses(ExpenseQueryRequest req);

	Mono<Integer> countExpenses(ExpenseQueryRequest req);

	Mono<Expense> getExpense(UUID expenseId);

	Mono<Expense> createExpense(Expense expense);

	Mono<Expense> updateExpense(UUID expenseId, ExpenseUpdateRequest req);

	Mono<Void> deleteExpense(UUID expenseId);
}

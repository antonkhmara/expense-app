package by.khmara.godel.application.expense.controllers;

import by.khmara.godel.application.expense.models.Expense;
import by.khmara.godel.application.expense.services.ExpenseService;
import by.khmara.godel.contract.expense.ExpenseContract;
import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@Validated
@RequiredArgsConstructor
public class ExpenseController implements ExpenseContract {
	private final ExpenseService expenseService;

	@Override
	public Mono<ExpenseResponse> getExpense(@NonNull UUID expenseId) {
		return expenseService.getExpense(expenseId)
			.map(Expense::asResponse);
	}

	@Override
	public Mono<ExpenseResponse> createExpense(@Valid @Body ExpenseCreateRequest req) {
		return expenseService
			.createExpense(new Expense(req.description(), req.category(), req.amount()))
			.map(Expense::asResponse);
	}

	@Override
	public Mono<ExpenseResponse> updateExpense(UUID expenseId, @Valid @Body ExpenseUpdateRequest req) {
		return expenseService.updateExpense(expenseId, req)
			.map(Expense::asResponse);
	}

	@Override
	public Mono<Long> deleteExpense(UUID expanseId) {
		return expenseService.deleteExpense(expanseId);
	}
}

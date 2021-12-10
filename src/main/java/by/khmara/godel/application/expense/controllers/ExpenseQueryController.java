package by.khmara.godel.application.expense.controllers;

import by.khmara.godel.application.expense.models.Expense;
import by.khmara.godel.application.expense.services.ExpenseService;
import by.khmara.godel.contract.expense.ExpenseQueryContract;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.response.CountResponse;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller
@Validated
@RequiredArgsConstructor
public class ExpenseQueryController implements ExpenseQueryContract {
	private final ExpenseService expenseService;

	@Override
	public Flux<ExpenseResponse> getExpenses(@Valid ExpenseQueryRequest req) {
		return expenseService.getExpenses(req)
			.map(Expense::asResponse);
	}

	@Override
	public Mono<CountResponse> countExpenses(@Valid ExpenseQueryRequest req) {
		return expenseService.countExpenses(req)
			.map(CountResponse::new);
	}
}

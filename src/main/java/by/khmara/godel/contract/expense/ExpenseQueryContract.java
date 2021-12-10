package by.khmara.godel.contract.expense;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import by.khmara.godel.contract.expense.response.CountResponse;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface ExpenseQueryContract {
	@Get("/expenses/v1/search{?req*}")
	Flux<ExpenseResponse> getExpenses(@Valid ExpenseQueryRequest req);

	@Get("/expenses/v1/count{?req*}")
	Mono<CountResponse> countExpenses(@Valid ExpenseQueryRequest req);
}

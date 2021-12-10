package by.khmara.godel.contract.expense;

import by.khmara.godel.contract.expense.request.ExpenseCreateRequest;
import by.khmara.godel.contract.expense.request.ExpenseUpdateRequest;
import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import lombok.NonNull;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

public interface ExpenseContract {
	@Get("/expense/v1/expanses/{expenseId}")
	Mono<ExpenseResponse> getExpense(@NonNull UUID expenseId);

	@Post("/expense/v1/expanses")
	Mono<ExpenseResponse> createExpense(@Valid @Body ExpenseCreateRequest req);

	@Put("/expense/v1/{expenseId}")
	Mono<ExpenseResponse> updateExpense(UUID expenseId, @Valid @Body ExpenseUpdateRequest req);

	@Delete("/expense/v1/{expanseId}")
	Mono<Long> deleteExpense(@NonNull UUID expanseId);
}

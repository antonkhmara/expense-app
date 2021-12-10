package by.khmara.godel.application.expense.models;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface ExpenseRepository extends ReactiveStreamsCrudRepository<Expense, UUID> {

	@Override
	@NonNull
	@Join(value = "category", type = Join.Type.FETCH)
	Mono<Expense> findById(@NonNull UUID id);

	@Override
	@NonNull
	Mono<Expense> save(@Valid @NonNull Expense expense);

	@Override
	@NonNull
	Mono<Expense> update(@Valid @NonNull Expense expense);

	@Override
	@NonNull
	Mono<Long> deleteById(@NotNull @NonNull UUID id);
}

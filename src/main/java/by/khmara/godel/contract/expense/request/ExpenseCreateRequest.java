package by.khmara.godel.contract.expense.request;

import by.khmara.godel.application.expense.models.Category;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public record ExpenseCreateRequest(
	@NotBlank String description,
	@NotNull Category category,
	@NotNull Double amount
) {
}

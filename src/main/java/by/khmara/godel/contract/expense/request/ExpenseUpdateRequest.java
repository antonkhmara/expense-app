package by.khmara.godel.contract.expense.request;

import by.khmara.godel.application.expense.models.Category;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Introspected
public record ExpenseUpdateRequest(
	@NotBlank String description,
	@NotNull LocalDateTime createdAt,
	@NotNull Category category,
	@NotNull Double amount
) {
}

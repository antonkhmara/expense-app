package by.khmara.godel.contract.expense.response;

import by.khmara.godel.application.expense.models.Category;
import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;
import java.util.UUID;

@Introspected
public record ExpenseResponse(
	UUID id,
	String description,
	LocalDateTime createdAt,
	Category category,
	double amount
) {
}

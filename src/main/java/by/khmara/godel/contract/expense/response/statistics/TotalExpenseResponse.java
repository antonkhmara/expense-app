package by.khmara.godel.contract.expense.response.statistics;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record TotalExpenseResponse(
	String categoryName,
	Double amount
) {
}

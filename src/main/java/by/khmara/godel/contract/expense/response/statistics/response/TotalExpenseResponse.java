package by.khmara.godel.contract.expense.response.statistics.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record TotalExpenseResponse(
	String categoryName,
	Double amount
) {
}

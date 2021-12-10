package by.khmara.godel.contract.expense.response.statistics.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record ExceededLimitByCategoriesResponse(
	String categoryName,
	Double amount
) {
}

package by.khmara.godel.contract.expense.response.statistics.response;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;

@Introspected
public record DateWithoutExpensesResponse(LocalDateTime date) {
}

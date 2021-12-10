package by.khmara.godel.contract.expense.response;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record CountResponse(Integer total) {
}

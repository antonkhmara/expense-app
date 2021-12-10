package by.khmara.godel.contract.expense.response.statistics.request;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Introspected
public record DatesIntervalRequest(
	@NotNull LocalDateTime fromDate,
	@NotNull LocalDateTime toDate
) {
}

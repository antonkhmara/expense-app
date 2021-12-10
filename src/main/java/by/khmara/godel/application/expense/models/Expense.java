package by.khmara.godel.application.expense.models;

import by.khmara.godel.contract.expense.response.ExpenseResponse;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
public record Expense(
	@Id @AutoPopulated UUID id,
	String description,
	@DateCreated LocalDateTime createdAt,
	@ManyToOne(optional = false, fetch = FetchType.LAZY) Category category,
	double amount) {

	public Expense(String name, Category category, double amount) {
		this(null, name, null, category, amount);
	}

	public ExpenseResponse asResponse() {
		return new ExpenseResponse(id, description, createdAt, category, amount);
	}
}

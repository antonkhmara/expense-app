package by.khmara.godel.application.expense.models;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.UUID;

@MappedEntity
public record Category(
	@Id @AutoPopulated UUID id,
	String name
) {
}

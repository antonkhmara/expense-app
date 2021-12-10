package by.khmara.godel.application.expense.services.utils;

import by.khmara.godel.application.expense.models.Category;
import by.khmara.godel.application.expense.models.Expense;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDateTime;
import java.util.UUID;

public final class ConvertorUtil {
	public static Integer convertToCount(Row r, RowMetadata rm) {
		return r.get("count_expenses", Integer.class);
	}

	public static Expense convertToExpense(Row r, RowMetadata rm) {
		return new Expense(
			r.get("id", UUID.class),
			r.get("description", String.class),
			r.get("created_at", LocalDateTime.class),
			new Category(r.get("category_id", UUID.class), r.get("category_name", String.class)),
			r.get("amount", Double.class)
		);
	}
}

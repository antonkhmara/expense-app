package by.khmara.godel.application.expense.services.queries.conditions;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class WhereCondition implements Condition {
	@Override
	public String buildRule(ExpenseQueryRequest req) {
		if (req.getCategoryName() == null && req.getDateInterval() == null)
			return "";

		var builder = new StringBuilder(" WHERE ");
		if (req.getCategoryName() != null)
			builder.append(categorySubCondition(req.getCategoryName()));

		if (req.getCategoryName() != null && req.getDateInterval() != null)
			builder.append(" AND ");

		if (req.getDateInterval() != null)
			builder.append(dateSubCondition(req.getDateInterval()));

		return builder.toString();
	}

	private String categorySubCondition(String categoryName) {
		return "category_name='" + categoryName + "'";
	}

	private String dateSubCondition(List<LocalDateTime> dates) {
		var builder = new StringBuilder();

		var startDate = dates.get(0) != null ? dates.get(0) : null;
		var endDate = dates.get(1) != null ? dates.get(1) : null;

		if (startDate != null) builder.append("created_at>").append(startDate);
		if (startDate != null && endDate != null) builder.append(" AND ");
		if (endDate != null) builder.append("created_at<").append(endDate);

		return builder.toString();
	}
}

package by.khmara.godel.application.expense.services.queries.conditions;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;

@Singleton
public class WhereCondition implements Condition {
	@Override
	public String buildRule(ExpenseQueryRequest req) {
		if (req.getCategoryName() == null && req.getFromDate() == null && req.getToDate() == null)
			return "";

		var builder = new StringBuilder(" WHERE ");
		if (req.getCategoryName() != null)
			builder.append(categorySubCondition(req.getCategoryName()));

		if (req.getCategoryName() != null && (req.getFromDate() != null || req.getToDate() != null))
			builder.append(" AND ");

		builder.append(dateSubCondition(req.getFromDate(), req.getToDate()));

		return builder.toString();
	}

	private String categorySubCondition(String categoryName) {
		return "c.name='" + categoryName + "'";
	}

	private String dateSubCondition(LocalDateTime fromDate, LocalDateTime toDate) {
		var builder = new StringBuilder();

		if (fromDate != null) builder.append("created_at>'").append(fromDate).append("'");
		if (fromDate != null && toDate != null) builder.append(" AND ");
		if (toDate != null) builder.append("created_at<'").append(toDate).append("'");

		return builder.toString();
	}
}

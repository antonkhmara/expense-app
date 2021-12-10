package by.khmara.godel.application.expense.services.queries.conditions;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import jakarta.inject.Singleton;

import static by.khmara.godel.contract.expense.Constants.DEFAULT_SORT_FIELD;
import static by.khmara.godel.contract.expense.Constants.DEFAULT_SORT_ORDER;
import static java.lang.String.format;

@Singleton
public class OrderCondition implements Condition {
	@Override
	public String buildRule(ExpenseQueryRequest req) {
		String sortField = req.getSortField() == null ? DEFAULT_SORT_FIELD : req.getSortField();
		String sortOrder = req.getSortOrder() == null ? DEFAULT_SORT_ORDER : req.getSortOrder();

		return buildOrderQuery(sortField, sortOrder);
	}

	private String buildOrderQuery(String sortField, String sortOrder) {
		return format(" ORDER BY %s %s", sortField, sortOrder);
	}
}

package by.khmara.godel.application.expense.services.queries.conditions;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;

public interface Condition {
	String buildRule(ExpenseQueryRequest req);
}

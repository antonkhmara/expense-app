package by.khmara.godel.application.expense.services.queries;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;

public interface QueryBuilder {
	String buildSelectQuery(ExpenseQueryRequest req);

	String buildCountQuery(ExpenseQueryRequest req);
}

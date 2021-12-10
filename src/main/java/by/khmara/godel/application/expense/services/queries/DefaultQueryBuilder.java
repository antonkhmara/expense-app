package by.khmara.godel.application.expense.services.queries;

import by.khmara.godel.application.expense.services.queries.conditions.OrderCondition;
import by.khmara.godel.application.expense.services.queries.conditions.PageCondition;
import by.khmara.godel.application.expense.services.queries.conditions.WhereCondition;
import by.khmara.godel.application.expense.services.queries.constants.QueryConstants;
import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DefaultQueryBuilder implements QueryBuilder {
	private final WhereCondition whereCondition;
	private final OrderCondition orderCondition;
	private final PageCondition pageCondition;

	@Override
	public String buildSelectQuery(ExpenseQueryRequest req) {
		return QueryConstants.SELECT_QUERY + whereRule(req) + orderRule(req) + pageRule(req) + ";";
	}

	@Override
	public String buildCountQuery(ExpenseQueryRequest req) {
		return QueryConstants.COUNT_QUERY + whereRule(req);
	}

	private String whereRule(ExpenseQueryRequest req) {
		return whereCondition.buildRule(req);
	}

	private String orderRule(ExpenseQueryRequest req) {
		return orderCondition.buildRule(req);
	}

	private String pageRule(ExpenseQueryRequest req) {
		return pageCondition.buildRule(req);
	}
}

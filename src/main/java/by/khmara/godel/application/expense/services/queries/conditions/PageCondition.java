package by.khmara.godel.application.expense.services.queries.conditions;

import by.khmara.godel.contract.expense.request.ExpenseQueryRequest;
import jakarta.inject.Singleton;

import static by.khmara.godel.contract.expense.Constants.DEFAULT_PAGE_NUMB;
import static by.khmara.godel.contract.expense.Constants.DEFAULT_PAGE_SIZE;

@Singleton
public class PageCondition implements Condition {
	@Override
	public String buildRule(ExpenseQueryRequest req) {
		int pageNumber = req.getPage() == null ? DEFAULT_PAGE_NUMB : req.getPage();
		int pageSize = req.getPageSize() == null ? DEFAULT_PAGE_SIZE : req.getPageSize();

		return buildPageQuery(pageNumber, pageSize);
	}

	private String buildPageQuery(int pageNumber, int pageSize) {
		return " LIMIT " + pageSize + " OFFSET " + pageSize * (pageNumber - 1);
	}
}

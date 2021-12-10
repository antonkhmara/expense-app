package by.khmara.godel.application.expense.services.queries;

import by.khmara.godel.application.expense.services.queries.conditions.OrderCondition;
import by.khmara.godel.application.expense.services.queries.conditions.PageCondition;
import by.khmara.godel.application.expense.services.queries.conditions.WhereCondition;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static by.khmara.godel.application.TestUtils.expenseQueryRequest;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultQueryBuilderTest {

	WhereCondition where = new WhereCondition();
	OrderCondition order = new OrderCondition();
	PageCondition page = new PageCondition();
	DefaultQueryBuilder builder = new DefaultQueryBuilder(where, order, page);

	@Test
	void should() {
		var request = expenseQueryRequest(LocalDateTime.now().minusDays(1), LocalDateTime.now());
		var x = builder.buildSelectQuery(request);

		assertThat(x).isEqualTo("");
	}
}

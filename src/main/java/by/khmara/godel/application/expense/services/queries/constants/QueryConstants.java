package by.khmara.godel.application.expense.services.queries.constants;

public interface QueryConstants {
	String SELECT_QUERY = """
				SELECT e.id, e.description, e.created_at, e.category_id, e.amount, c.name AS category_name
				FROM expense e
				JOIN category c on e.category_id = c.id
		""";

	String COUNT_QUERY = """
					SELECT COUNT(*) AS count_expenses
					FROM expense
		""";
}

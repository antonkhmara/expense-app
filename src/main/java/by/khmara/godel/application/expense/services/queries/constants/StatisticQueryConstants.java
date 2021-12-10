package by.khmara.godel.application.expense.services.queries.constants;

public interface StatisticQueryConstants {
	String TOTAL_SUM_OF_AMOUNT = """
				SELECT sum(amount) AS total
		  	FROM expense;
		""";

	String TOTAL_SUM_OF_AMOUNT_BY_CATEGORIES = """
				SELECT name, sum(amount) AS total
				FROM expense e
		    JOIN category c ON c.id = e.category_id
		  	GROUP BY c.name
		""";

	String MOST_EXPENSIVE_MONTH = """
				SELECT t.month AS month, max(total) AS total FROM
		          (
		          SELECT EXTRACT(MONTH FROM created_at) as month, sum(amount) AS total
		          FROM expense e
		          GROUP BY e.created_at
		          ) as t
				GROUP BY t.month
		""";

	String TOTAL_EXPENSES_BY_PRESENT_MONTH = """
				SELECT c.name AS name, sum(e.amount) AS total
				FROM expense e
				JOIN category c ON c.id = e.category_id
				WHERE EXTRACT(MONTH FROM e.created_at) = EXTRACT(MONTH FROM NOW())
				GROUP BY c.name
		""";

	String TOTAL_EXPENSES_BY_PREVIOUS_MONTH = """
				SELECT c.name AS name, sum(e.amount) AS total
				FROM expense e
				JOIN category c ON c.id = e.category_id
				WHERE EXTRACT(MONTH FROM e.created_at) = EXTRACT(MONTH FROM NOW()) - 1
				GROUP BY c.name
		""";

	String LIST_OF_EXPENSES_BY_CATEGORY = """
				SELECT e.description AS description
		  	FROM expense e
		  	JOIN category c ON c.id = e.category_id
		  	WHERE c.name = '%s'
		  	ORDER BY c.name
		""";

	String SEARCH_DAY_WITHOUT_EXPENSES = """
				SELECT e.created_at
			  FROM expense e
			  JOIN category c ON c.id = e.category_id
		  	WHERE (created_at BETWEEN '%s' AND '%s') AND amount = 0
		  	ORDER BY created_at DESC
		  	LIMIT 1
		""";
}

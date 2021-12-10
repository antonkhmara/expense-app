package by.khmara.godel.contract.expense;

public interface Constants {
	String DEFAULT_SORT_FIELD = "created_at";
	String DEFAULT_SORT_ORDER = "DESC";
	int DEFAULT_PAGE_SIZE = 25;
	int DEFAULT_PAGE_NUMB = 1;

	interface V8NMessages {
		String DESCRIPTION_IS_BLANK = "expense-app.validation.descriptionIsBlank";
		String SORT_FIELD_IS_INVALID = "expense-app..validation.invalidSortField";
		String SORT_ORDER_IS_INVALID = "expense-app..validation.invalidSortOrder";
		String PAGE_IS_INVALID = "expense-app..validation.invalidPage";
		String PAGE_SIZE_IS_INVALID = "expense-app..validation.invalidPageSize";
	}
}

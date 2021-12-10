package by.khmara.godel.contract.expense.request;

import by.khmara.godel.contract.expense.Constants.V8NMessages;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static by.khmara.godel.contract.expense.Constants.DEFAULT_PAGE_NUMB;
import static by.khmara.godel.contract.expense.Constants.DEFAULT_PAGE_SIZE;
import static by.khmara.godel.contract.expense.Constants.DEFAULT_SORT_FIELD;
import static by.khmara.godel.contract.expense.Constants.DEFAULT_SORT_ORDER;

@Data
@Introspected
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseQueryRequest {
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private String categoryName;
	@Pattern(regexp = "description|created_at|amount|category", message = V8NMessages.SORT_FIELD_IS_INVALID)
	private String sortField = DEFAULT_SORT_FIELD;
	@Pattern(regexp = "ASC|DESC", message = V8NMessages.SORT_ORDER_IS_INVALID)
	private String sortOrder = DEFAULT_SORT_ORDER;
	@Min(value = 1, message = V8NMessages.PAGE_IS_INVALID)
	private Integer page = DEFAULT_PAGE_NUMB;
	@Min(value = 1, message = V8NMessages.PAGE_SIZE_IS_INVALID)
	private Integer pageSize = DEFAULT_PAGE_SIZE;

	public ExpenseQueryRequest(LocalDateTime fromDate, LocalDateTime toDate, String categoryName) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.categoryName = categoryName;
	}
}

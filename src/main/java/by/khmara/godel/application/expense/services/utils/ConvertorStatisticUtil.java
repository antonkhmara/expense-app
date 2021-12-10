package by.khmara.godel.application.expense.services.utils;

import by.khmara.godel.contract.expense.response.statistics.response.DateWithoutExpensesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExceededLimitByCategoriesResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpenseByCategoryResponse;
import by.khmara.godel.contract.expense.response.statistics.response.ExpensesByMonthResponse;
import by.khmara.godel.contract.expense.response.statistics.response.TotalExpenseResponse;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDateTime;

public final class ConvertorStatisticUtil {
	public static Integer convertToTotalSum(Row r, RowMetadata rm) {
		return r.get("total", Integer.class);
	}

	public static TotalExpenseResponse convertToTotalExpenseResponse(Row r, RowMetadata rm) {
		return new TotalExpenseResponse(r.get("name", String.class), r.get("total", Double.class));
	}

	public static ExpensesByMonthResponse convertToMostExpensiveMonthResponse(Row r, RowMetadata rm) {
		return new ExpensesByMonthResponse(r.get("month", Integer.class), r.get("total", Double.class));
	}

	public static ExceededLimitByCategoriesResponse convertToExceededLimitByCategoriesResponse(Row r, RowMetadata rm) {
		return new ExceededLimitByCategoriesResponse(r.get("name", String.class), r.get("total", Double.class));
	}

	public static ExpenseByCategoryResponse convertToExpenseByCategoryResponse(Row r, RowMetadata rm) {
		return new ExpenseByCategoryResponse(r.get("description", String.class));
	}

	public static DateWithoutExpensesResponse convertToDateWithoutExpensesResponse(Row r, RowMetadata rm) {
		return new DateWithoutExpensesResponse(r.get("created_at", LocalDateTime.class));
	}
}

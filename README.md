# Expense-application

1. Clone this repository and run application with command `./gradlew run`
2. Download .jar file from release section on github and execute it

## Using the application

### CRUD operations

- #### `http://localhost:8080/expense/v1/expanses/{expenseId}` - get expense by id
- #### `http://localhost:8080/expense/v1/expanses` - create expense
- #### `http://localhost:8080/expense/v1/{expenseId}` - update expense
- #### `http://localhost:8080/expense/v1/{expenseId}` - delete expense by id

### Search operations

- #### `http://localhost:8080/expense/v1/search{?req*}` - search expenses with filter
- #### `http://localhost:8080/expense/v1/count{?req*}` - count expenses with filter

### Statistics:

- #### `http://localhost:8080/expense/v1/statistics/totalAmount` - get total amount of all expenses
- #### `http://localhost:8080/expense/v1/statistics/totalExpenseByCategories` - get total expenses by month
- #### `http://localhost:8080/expense/v1/statistics/theMostExpensiveMonth` - get the most expensive month
- #### `http://localhost:8080/expense/v1/statistics/categoriesWithExceededLimit` - get categories where exceeded limit compare to previous month
- #### `http://localhost:8080/expense/v1/statistics/expensesByCategories/{categoryName}` - get expenses by concrete category
- #### `http://localhost:8080/expense/v1/statistics/dayWithOutExpenses{?req*}` - get day without expenses from interval


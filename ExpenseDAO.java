import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private Connection conn;

    public ExpenseDAO() {
        try {
            conn = DatabaseConnection.getConnection();
            // Create table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "description VARCHAR(255), " +
                    "amount DOUBLE, " +
                    "category VARCHAR(50))";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(Expense expense) {
        try {
            String sql = "INSERT INTO expenses (description, amount, category) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, expense.getDescription());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM expenses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Expense expense = new Expense(rs.getString("description"),
                        rs.getDouble("amount"), rs.getString("category"));
                expense.setId(rs.getInt("id"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}

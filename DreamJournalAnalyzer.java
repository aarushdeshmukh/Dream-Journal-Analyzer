// Dream Journal Analyzer with MySQL Integration
// Updated by Aarush Deshmukh
// MySQL password = 1234

import java.sql.*;
import java.util.*;

public class DreamJournalAnalyzer {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dream_journal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DreamJournalAnalyzer app = new DreamJournalAnalyzer();
        app.run();
    }

    private void run() {
        System.out.println("Welcome to Dream Journal Analyzer with MySQL!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new dream entry");
            System.out.println("2. View all entries");
            System.out.println("3. Search entries by theme");
            System.out.println("4. Generate analysis report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEntry();
                case 2 -> viewEntries();
                case 3 -> searchByTheme();
                case 4 -> generateReport();
                case 5 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addEntry() {
        System.out.print("Enter dream description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter theme: ");
        String theme = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO dreams(description, theme, date) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, desc);
            pst.setString(2, theme);
            pst.setString(3, date);
            pst.executeUpdate();

            System.out.println("Dream entry added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewEntries() {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM dreams";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n--- All Dream Entries ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Theme: " + rs.getString("theme"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByTheme() {
        System.out.print("Enter theme: ");
        String theme = scanner.nextLine();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM dreams WHERE theme LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + theme + "%");
            ResultSet rs = pst.executeQuery();

            System.out.println("\n--- Search Results ---");
            while (rs.next()) {
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Theme: " + rs.getString("theme"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateReport() {
        System.out.println("\n--- Analysis Report ---");

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Count total entries
            String totalSql = "SELECT COUNT(*) AS total FROM dreams";
            ResultSet totalRS = con.createStatement().executeQuery(totalSql);
            if (totalRS.next()) {
                System.out.println("Total Dream Entries: " + totalRS.getInt("total"));
            }

            // Count by theme
            String themeSql = "SELECT theme, COUNT(*) AS count FROM dreams GROUP BY theme";
            ResultSet themeRS = con.createStatement().executeQuery(themeSql);

            System.out.println("\nEntries By Theme:");
            while (themeRS.next()) {
                System.out.println(themeRS.getString("theme") + " : " + themeRS.getInt("count"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

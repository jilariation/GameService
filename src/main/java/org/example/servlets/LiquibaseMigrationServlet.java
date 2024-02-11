package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.example.connection.LiquibaseMigration;

@WebServlet("/migrate")
public class LiquibaseMigrationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        LiquibaseMigration.getMigrations();
    }
}

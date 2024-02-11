package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aspects.annotations.Monitor;
import org.example.repository.entity.Transaction;
import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;
import org.example.service.TransactionService;
import org.example.service.TransactionServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@Monitor
@WebServlet("/transaction_history")
public class TransactionHistoryServlet extends HttpServlet {
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final PlayerService playerService = new PlayerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        for(Transaction transaction : transactionService.transactionHistory(playerService.getPlayer())) {
            jsonArray.put(getTransaction(
                    transaction.getId(),
                    transaction.getValue(),
                    transaction.getTransactionType().toString()
            ));
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonArray);
        printWriter.flush();
    }
    private static JSONObject getTransaction(int id, double value, String type) {
        JSONObject transaction = new JSONObject();
        transaction.put("id", id);
        transaction.put("value", value);
        transaction.put("type", type);
        return transaction;
    }
}

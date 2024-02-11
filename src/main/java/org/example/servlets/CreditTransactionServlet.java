package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aspects.annotations.Monitor;
import org.example.service.TransactionService;
import org.example.service.TransactionServiceImpl;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Monitor
@WebServlet("/transaction/credit")
public class CreditTransactionServlet extends HttpServlet {
    private final TransactionService transactionService = new TransactionServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(catchJSON(req));
        int value = transactionService.creditTransaction(jsonRequest.getInt("value"),
                jsonRequest.getInt("valueId"));
        JSONObject jsonResponse = getJSONObject(value);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
    private JSONObject getJSONObject(int val) {
        JSONObject jsonObject = new JSONObject();
        if (val == 1) {
            jsonObject.put("message", "identifier is not unique");
        } else {
            jsonObject.put("message", "operation completed successfully");
        }
        return jsonObject;
    }
    private String catchJSON(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}

package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aspects.annotations.Monitor;
import org.example.repository.dto.PlayerDTO;
import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;
import org.example.validator.PlayerDTOValidator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@Monitor
@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(catchJSON(request));
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(jsonRequest.getString("username"));
        playerDTO.setPassword(jsonRequest.getString("password"));
        new PlayerDTOValidator().validate(playerDTO);
        playerService.registrationPlayer(playerDTO.getName(), playerDTO.getPassword());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "User registered successfully");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
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

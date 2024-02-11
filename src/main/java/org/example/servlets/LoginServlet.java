package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.aspects.annotations.Monitor;
import org.example.repository.dto.PlayerDTO;
import org.example.repository.dto.PlayerMapperImpl;
import org.example.repository.entity.Player;
import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;
import org.example.validator.PlayerDTOValidator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Monitor
@WebServlet("/log")
public class LoginServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(catchJSON(req));
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(jsonRequest.getString("username"));
        playerDTO.setPassword(jsonRequest.getString("password"));
        new PlayerDTOValidator().validate(playerDTO);
        Player player = new PlayerMapperImpl().playerDTOToPlayer(playerDTO);
        playerService.loginPlayer(player.getName(), player.getPassword());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("message", "User auth successfully");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
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

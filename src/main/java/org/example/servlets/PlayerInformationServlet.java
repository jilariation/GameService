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
import org.example.repository.entity.PlayerHistory;
import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@Monitor
@WebServlet("/player")
public class PlayerInformationServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player player = playerService.getPlayer();
        playerService.addEnumToDatabase(player.getId(), PlayerHistory.INFO);
        PlayerDTO playerDTO = new PlayerMapperImpl().playerToPlayerDTO(player);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("name", playerDTO.getName());
        jsonResponse.put("password", playerDTO.getPassword());
        jsonResponse.put("balance", playerDTO.getBalance());
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonResponse);
        printWriter.flush();
    }
}

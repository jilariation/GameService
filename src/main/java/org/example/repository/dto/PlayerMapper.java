package org.example.repository.dto;

import org.example.repository.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "balance", target = "balance")
    PlayerDTO playerToPlayerDTO(Player player);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "balance", target = "balance")
    Player playerDTOToPlayer(PlayerDTO playerDTO);
}

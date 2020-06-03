package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Game;

import java.util.List;

@Mapper
public interface GameMapper {

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, wynik_pierwszego, wynik_drugiego FROM mecze")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego")
    })
    List<Game> findAll();

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, wynik_pierwszego, wynik_drugiego FROM mecze WHERE miejsce = #{place}")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego")
    })
    List<Game> findAllByPlace(String place);

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, wynik_pierwszego, wynik_drugiego FROM mecze WHERE id_meczu = #{id}")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego")
    })
    Game findById(int id);

    @Delete("DELETE FROM mecze WHERE id_meczu = #{id}")
    int deleteById(int id);

    @Delete("DELETE FROM zawodnicy_mecze WHERE id_meczu = #{id}")
    int deleteGamePlayerById(int id);

    @Insert("INSERT INTO mecze (id_kolejki, termin, miejsce, wynik_pierwszego, wynik_drugiego) " +
            " VALUES (#{roundId}, #{gameDate}, #{place}, #{firstPlayerScore}, #{secondPlayerScore})")
    @Options(useGeneratedKeys = true, keyProperty = "gameId")
    int insert(Game game);

    @Update("UPDATE mecze SET id_kolejki=#{roundId}, termin=#{gameDate}, " +
            "miejsce=#{place}, wynik_pierwszego=#{firstPlayerScore} , wynik_drugiego=#{secondPlayerScore}  " +
            "WHERE id_meczu=#{gameId}")
    int update(Game game);

    @Update("UPDATE zawodnicy_mecze SET id_meczu=#{roundId}, id_zawodnika=#{playerId} " +
            "WHERE id_meczu=#{gameId} AND id_zawodnika=#{playerId}")
    int updateGamePlayer(int gameId, int playerId);

    @Insert("INSERT INTO zawodnicy_mecze (id_meczu, id_zawodnika) " +
            " VALUES (#{gameId}, #{playerId})")
    int insertGamePlayer(int gameId, int playerId);
}

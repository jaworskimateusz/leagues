package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Game;

import java.util.List;

@Mapper
public interface GameMapper {

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, " +
            "wynik_pierwszego, wynik_drugiego, id_pierwszego_zawodnika, id_drugiego_zawodnika FROM mecze")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego"),
            @Result(property = "firstPlayerId", column = "id_pierwszego_zawodnika"),
            @Result(property = "secondPlayerId", column = "id_drugiego_zawodnika")
    })
    List<Game> findAll();

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, " +
            "wynik_pierwszego, wynik_drugiego, id_pierwszego_zawodnika, id_drugiego_zawodnika FROM mecze " +
            "WHERE miejsce = #{place}")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego"),
            @Result(property = "firstPlayerId", column = "id_pierwszego_zawodnika"),
            @Result(property = "secondPlayerId", column = "id_drugiego_zawodnika")
    })
    List<Game> findAllByPlace(String place);

    @Select("SELECT id_meczu, id_kolejki, termin, miejsce, " +
            "wynik_pierwszego, wynik_drugiego, id_pierwszego_zawodnika, id_drugiego_zawodnika FROM mecze " +
            "WHERE id_meczu = #{id}")
    @Results({
            @Result(property = "gameId", column = "id_meczu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "gameDate", column = "termin"),
            @Result(property = "place", column = "miejsce"),
            @Result(property = "firstPlayerScore", column = "wynik_pierwszego"),
            @Result(property = "secondPlayerScore", column = "wynik_drugiego"),
            @Result(property = "firstPlayerId", column = "id_pierwszego_zawodnika"),
            @Result(property = "secondPlayerId", column = "id_drugiego_zawodnika")
    })
    Game findById(int id);

    @Delete("DELETE FROM mecze WHERE id_meczu = #{id}")
    int deleteById(int id);

    @Delete("DELETE FROM zawodnicy_mecze WHERE id_meczu = #{id}")
    int deleteGamePlayerById(int id);

    @Insert("INSERT INTO mecze (id_kolejki, termin, miejsce, wynik_pierwszego, wynik_drugiego) " +
            " VALUES (#{roundId}, #{gameDate}, #{place}, #{firstPlayerScore}, #{secondPlayerScore})")
    int insert(Game game);

    @Update("UPDATE mecze SET id_kolejki=#{roundId}, termin=#{gameDate}, " +
            "miejsce=#{place}, wynik_pierwszego=#{firstPlayerScore} , wynik_drugiego=#{secondPlayerScore} " +
            "WHERE id_meczu=#{gameId}")
    int update(Game game);

    @Update("UPDATE mecze SET id_pierwszego_zawodnika=#{firstPlayerId} WHERE id_meczu=#{gameId}")
    int updateFirstPlayerId(int firstPlayerId, int gameId);

    @Update("UPDATE mecze SET id_drugiego_zawodnika=#{secondPlayerId} WHERE id_meczu=#{gameId}")
    int updateSecondPlayerId(int secondPlayerId, int gameId);


    @Select("SELECT MAX(id_meczu) FROM mecze")
    Integer findMaxGameId();

}

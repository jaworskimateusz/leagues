package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Player;

import java.util.List;

@Mapper
public interface PlayerMapper {

    @Select("SELECT id_zawodnika, imie, nazwisko, pesel FROM zawodnicy")
    @Results({
            @Result(property = "playerId", column = "id_zawodnika"),
            @Result(property = "name", column = "imie"),
            @Result(property = "surname", column = "nazwisko"),
            @Result(property = "pesel", column = "pesel")
    })
    List<Player> findAll();

    @Select("SELECT id_zawodnika, imie, nazwisko, pesel FROM zawodnicy WHERE id_zawodnika = #{id}")
    @Results({
            @Result(property = "playerId", column = "id_zawodnika"),
            @Result(property = "name", column = "imie"),
            @Result(property = "surname", column = "nazwisko"),
            @Result(property = "pesel", column = "pesel")
    })
    Player findById(int id);

    @Delete("DELETE FROM zawodnicy WHERE id_zawodnika = #{id}")
    int deleteById(int id);

    @Delete("DELETE FROM zawodnicy_ligi WHERE id_zawodnika = #{id}")
    int deletePlayerLeagueById(int id);

    @Insert("INSERT INTO zawodnicy (imie, nazwisko, pesel) " +
            " VALUES (#{name}, #{surname}, #{pesel})")
    @Options(useGeneratedKeys = true, keyProperty = "playerId")
    int insert(Player player);

    @Update("UPDATE zawodnicy SET imie=#{name}, nazwisko=#{surname}, pesel=#{pesel} WHERE id_zawodnika=#{playerId}")
    int update(Player player);

    @Update("UPDATE zawodnicy_ligi SET id_zawodnika=#{playerId}, id_ligi=#{leagueId} " +
            "WHERE id_zawodnika=#{playerId} AND id_ligi=#{leagueId}")
    int updatePlayerLeague(int playerId, int leagueId);

    @Insert("INSERT INTO zawodnicy_ligi (id_zawodnika, id_ligi) " +
            " VALUES (#{playerId}, #{leagueId})")
    int insertPlayerLeague(int playerId, int leagueId);


    @Select("SELECT id_zawodnika, imie, nazwisko, pesel FROM zawodnicy WHERE pesel = #{pesel}")
    @Results({
            @Result(property = "playerId", column = "id_zawodnika"),
            @Result(property = "name", column = "imie"),
            @Result(property = "surname", column = "nazwisko"),
            @Result(property = "pesel", column = "pesel")
    })
    List<Player> searchBy(String pesel);

}

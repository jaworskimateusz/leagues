package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Season;

import java.util.Date;
import java.util.List;

@Mapper
public interface SeasonMapper {
    @Select("SELECT id_sezonu, id_ligi, numer, opis FROM sezony")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "leagueId", column = "id_ligi"),
            @Result(property = "number", column = "numer"),
            @Result(property = "description", column = "opis")
    })
    List<Season> findAll();

    @Select("SELECT id_sezonu, id_ligi, numer, opis FROM sezony WHERE numer = #{number}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "leagueId", column = "id_ligi"),
            @Result(property = "number", column = "numer"),
            @Result(property = "description", column = "opis")
    })
    List<Season> findAllByNumber(int number);

    @Select("SELECT sezony.*, wpisowe.id_wpisowe as id_wpis, wpisowe.zaplacono as zap " +
            "FROM zawodnicy\n" +
            "    JOIN zawodnicy_ligi USING (id_zawodnika)\n" +
            "    JOIN ligi USING (id_ligi)\n" +
            "    JOIN sezony USING (id_ligi)\n" +
            "    JOIN sezony_wpisowe USING (id_sezonu)\n" +
            "    JOIN wpisowe USING (id_wpisowe)\n" +
            "WHERE zawodnicy.id_zawodnika = ${playerId}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "leagueId", column = "id_ligi"),
            @Result(property = "number", column = "numer"),
            @Result(property = "description", column = "opis"),
            @Result(property = "feeId", column = "id_wpis"),
            @Result(property = "confirmed", column = "zap")
    })
    List<Season> findAllByPlayerId(int playerId);

    @Select("SELECT id_sezonu, id_ligi, numer, opis FROM sezony WHERE id_sezonu = #{id}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "leagueId", column = "id_ligi"),
            @Result(property = "number", column = "numer"),
            @Result(property = "description", column = "opis")
    })
    Season findById(int id);

    @Delete("DELETE FROM sezony WHERE id_sezonu = #{id}")
    int deleteById(int id);

    @Delete("DELETE IGNORE FROM sezony_wpisowe WHERE id_sezonu = #{id}")
    int deleteSeasonFeeById(int id);

    @Insert("INSERT INTO sezony (id_ligi, numer, opis) " +
            " VALUES (#{leagueId}, #{number}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "seasonId")
    int insert(Season season);

    @Update("UPDATE sezony SET id_ligi=#{leagueId}, numer=#{number}, opis=#{description} WHERE id_sezonu=#{seasonId}")
    int update(Season season);

    @Insert("INSERT INTO sezony_wpisowe (id_sezonu, id_wpisowe) " +
            " VALUES (#{seasonId}, #{feeId})")
    int insertSeasonFee(int seasonId, int feeId);

    @Update("UPDATE wpisowe SET zaplacono=1 WHERE id_wpisowe=#{feeId}")
    int confirmSeasonFee(int feeId);

    @Update("UPDATE wpisowe SET zaplacono=0 WHERE id_wpisowe=#{feeId}")
    int cancelSeasonFee(int feeId);

    @Insert("INSERT INTO wpisowe (typ, data_uiszczenia, zaplacono) " +
            " VALUES (1, #{date}, 0)")
    int insertFee(Date date);

    @Select("SELECT MAX(id_wpisowe) FROM wpisowe")
    int findLastFeeId();

}

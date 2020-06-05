package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Round;

import java.util.Date;
import java.util.List;

@Mapper
public interface RoundMapper {

    @Select("SELECT id_kolejki, id_sezonu, numer, dyscyplina FROM kolejki")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "number", column = "numer"),
            @Result(property = "discipline", column = "dyscyplina")
    })
    List<Round> findAll();

    @Select("SELECT id_kolejki, id_sezonu, numer, dyscyplina FROM kolejki WHERE numer = #{number}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "number", column = "numer"),
            @Result(property = "discipline", column = "dyscyplina")
    })
    List<Round> findAllByNumber(int number);

    @Select("SELECT kolejki.*, wpisowe.id_wpisowe as id_wpis, wpisowe.zaplacono as zap " +
            "FROM zawodnicy\n" +
            "    JOIN zawodnicy_ligi USING (id_zawodnika)\n" +
            "    JOIN ligi USING (id_ligi)\n" +
            "    JOIN sezony USING (id_ligi)\n" +
            "    JOIN kolejki USING (id_sezonu)\n" +
            "    JOIN wpisowe_kolejki USING (id_kolejki)\n" +
            "    JOIN wpisowe USING (id_wpisowe)\n" +
            "WHERE zawodnicy.id_zawodnika = ${playerId}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "number", column = "numer"),
            @Result(property = "discipline", column = "dyscyplina"),
            @Result(property = "feeId", column = "id_wpis"),
            @Result(property = "confirmed", column = "zap")
    })
    List<Round> findAllByPlayerId(int playerId);

    @Select("SELECT id_kolejki, id_sezonu, numer, dyscyplina FROM kolejki WHERE id_kolejki = #{id}")
    @Results({
            @Result(property = "seasonId", column = "id_sezonu"),
            @Result(property = "roundId", column = "id_kolejki"),
            @Result(property = "number", column = "numer"),
            @Result(property = "discipline", column = "dyscyplina")
    })
    Round findById(int id);

    @Delete("DELETE FROM kolejki WHERE id_kolejki = #{id}")
    int deleteById(int id);

    @Delete("DELETE FROM wpisowe_kolejki WHERE id_kolejki = #{id}")
    int deleteRoundFeeById(int id);

    @Insert("INSERT INTO kolejki (id_sezonu, numer, dyscyplina) " +
            " VALUES (#{seasonId}, #{number}, #{discipline})")
    @Options(useGeneratedKeys = true, keyProperty = "roundId")
    int insert(Round round);

    @Update("UPDATE kolejki SET id_kolejki=#{roundId}, numer=#{number}, dyscyplina=#{discipline} WHERE id_kolejki=#{roundId}")
    int update(Round round);

    @Insert("INSERT INTO wpisowe_kolejki (id_kolejki, id_wpisowe) " +
            " VALUES (#{roundId}, #{feeId})")
    int insertRoundFee(int roundId, int feeId);

    @Update("UPDATE wpisowe SET zaplacono=1 WHERE id_wpisowe=#{feeId}")
    int confirmRoundFee(int feeId);

    @Update("UPDATE wpisowe SET zaplacono=0 WHERE id_wpisowe=#{feeId}")
    int cancelRoundFee(int feeId);

    @Insert("INSERT INTO wpisowe (typ, data_uiszczenia, zaplacono) " +
            " VALUES (0, #{date}, 0)")
    int insertFee(Date date);

    @Select("SELECT MAX(id_wpisowe) FROM wpisowe")
    int findLastFeeId();

}

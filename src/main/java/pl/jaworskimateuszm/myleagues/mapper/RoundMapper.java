package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Round;

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

    @Delete("DELETE FROM kolejki_wpisowe WHERE id_kolejki = #{id}")
    int deleteRoundFeeById(int id);

    @Insert("INSERT INTO kolejki (id_sezonu, numer, dyscyplina) " +
            " VALUES (#{seasonId}, #{seasonId}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "roundId")
    int insert(Round round);

    @Update("UPDATE kolejki SET id_kolejki=#{roundId}, numer=#{roundId}, opis=#{description} WHERE id_kolejki=#{roundId}")
    int update(Round round);

    @Update("UPDATE kolejki_wpisowe SET id_kolejki=#{roundId}, id_wpisowego=#{feeId} " +
            "WHERE id_kolejki=#{roundId} AND id_wpisowego=#{feeId}")
    int updateRoundFee(int roundId, int feeId);

    @Insert("INSERT INTO kolejki_wpisowe (id_kolejki, id_wpisowego) " +
            " VALUES (#{roundId}, #{feeId})")
    int insertRoundFee(int roundId, int feeId);
}

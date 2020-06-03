package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Season;

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
            " VALUES (#{leagueId}, #{leagueId}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "seasonId")
    int insert(Season season);

    @Update("UPDATE sezony SET id_ligi=#{leagueId}, numer=#{leagueId}, opis=#{description} WHERE id_sezonu=#{seasonId}")
    int update(Season season);

    @Update("UPDATE sezony_wpiowe SET id_sezonnu=#{seasonId}, id_wpisowego=#{feeId} " +
            "WHERE id_sezonnu=#{seasonId} AND id_wpisowego=#{feeId}")
    int updateSeasonFee(int seasonId, int feeId);

    @Insert("INSERT INTO sezony_wpiowe (id_sezonnu, id_wpisowego) " +
            " VALUES (#{seasonId}, #{feeId})")
    int insertSeasonFee(int seasonId, int feeId);

}

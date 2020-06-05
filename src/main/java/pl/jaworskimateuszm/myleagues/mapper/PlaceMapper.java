package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.Place;

import java.util.List;

@Mapper
public interface PlaceMapper {

    @Select("SELECT id_obiektu, koszt, liczba_godzin, nazwa, potwierdzono FROM obiekty")
    @Results({
            @Result(property = "placeId", column = "id_obiektu"),
            @Result(property = "cost", column = "koszt"),
            @Result(property = "numberOfHours", column = "liczba_godzin"),
            @Result(property = "name", column = "nazwa"),
            @Result(property = "confirmed", column = "potwierdzono")
    })
    List<Place> findAll();

    @Select("SELECT id_obiektu, koszt, liczba_godzin, nazwa, potwierdzono FROM obiekty WHERE nazwa = #{name}")
    @Results({
            @Result(property = "placeId", column = "id_obiektu"),
            @Result(property = "cost", column = "koszt"),
            @Result(property = "numberOfHours", column = "liczba_godzin"),
            @Result(property = "name", column = "nazwa"),
            @Result(property = "confirmed", column = "potwierdzono")
    })
    List<Place> findAllByName(String name);

    @Select("SELECT id_obiektu, koszt, liczba_godzin, nazwa, potwierdzono FROM obiekty WHERE id_obiektu = #{id}")
    @Results({
            @Result(property = "placeId", column = "id_obiektu"),
            @Result(property = "cost", column = "koszt"),
            @Result(property = "numberOfHours", column = "liczba_godzin"),
            @Result(property = "name", column = "nazwa"),
            @Result(property = "confirmed", column = "potwierdzono")
    })
    Place findById(int id);

    @Delete("DELETE FROM obiekty WHERE id_obiektu = #{id}")
    int deleteById(int id);

    @Insert("INSERT INTO obiektu (koszt, liczba_godzin, nazwa, potwierdzono) " +
            " VALUES (#{cost}, #{numberOfHours}, #{confirmed})")
    @Options(useGeneratedKeys = true, keyProperty = "placeId")
    int insert(Place place);

    @Update("UPDATE obiekty SET koszt=#{cost}, liczba_godzin=#{numberOfHours}, nazwa=#{name}," +
            " potwierdzono=#{confirmed} WHERE id_obiektu=#{placeId}")
    int update(Place place);

}

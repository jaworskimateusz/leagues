package pl.jaworskimateuszm.myleagues.mapper;

import org.apache.ibatis.annotations.*;
import pl.jaworskimateuszm.myleagues.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, enabled) " +
            "VALUES(#{username}, #{password}, #{enabled})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

//    @Results({
//            @Result(property = "login", column = "login"),
//            @Result(property = "password", column = "haslo"),
//            @Result(property = "name", column = "imie"),
//            @Result(property = "surname", column = "nazwisko")
//    })
    @Select("SELECT username, password, enabled FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users")
    List<User> findAll();
}

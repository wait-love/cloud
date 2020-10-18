package start.mapper1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jason
 * @Create: 2020/10/13  6:59
 * @Description 用户mapper
 */
@Repository
public interface UserMapper1 {

    @Insert("insert into test_user(name,age) values(#{name},#{age})")
    void addUser(@Param("name") String name, @Param("age") int age);
}

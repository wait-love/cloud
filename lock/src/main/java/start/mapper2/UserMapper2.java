package start.mapper2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jason
 * @Create: 2020/10/13  7:02
 * @Description 第二个数据的用户mapper
 */
@Repository  //mapper是需要配合xml来使用的
public interface UserMapper2 {
    @Insert("insert into test_user(name,age) values(#{name},#{age})")
    void addUser(@Param("name") String name, @Param("age") int age);
}

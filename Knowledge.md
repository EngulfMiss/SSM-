# SSM整合
**pom.xml**
```xml
    <!-- 依赖：junit，数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring -->
    <dependencies>
        <!-- Junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!-- 数据库驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
        </dependency>
        <!-- 数据库连接池：c3p0 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>

        <!-- Servlet - JSP -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>

        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.8.RELEASE</version>
        </dependency>
    </dependencies>

    <!-- 静态资源导出问题 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```

## Mybatis层
**Mybatis核心配置文件**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.engulf.pojo"/>
    </typeAliases>

    <!-- 数据源的配置交给了spring去做 -->
    
    <mappers>
        <mapper class="com.engulf.dao.BookMapper"></mapper>
    </mappers>
</configuration>
```

**pojo实体类**
```java
public class Books {
    private Integer bookID;
    private String bookName;
    private Integer bookCounts;
    private String detail;
    
    ...
}
```

**dao(mapper)接口**
```java
public interface BookMapper {
    //增加一条数据
    int addBook(Books books);
    //删除一条数据
    int delBook(@Param("id") int id);
    //修改一条数据
    int updateBook(Books books);
    //查询一条数据
    Books selectBookById(@Param("id") int id);
    //查询所有数据
    List<Books> selectAllBooks();
}
```

**mapper映射文件**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.engulf.dao.BookMapper">
    <select id="selectBookById" resultType="books">
        select * from ssmbuild.books where bookID = #{id}
    </select>
    <select id="selectAllBooks" resultType="books">
        select * from ssmbuild.books
    </select>
    <insert id="addBook" parameterType="books">
        insert into ssmbuild.books (bookID, bookName, bookCounts, detail)
        VALUES (#{bookID},#{bookName},#{bookCounts},#{detail})
    </insert>
    <delete id="delBook" parameterType="int">
        delete from ssmbuild.books where bookID = #{id}
    </delete>
    <update id="updateBook" parameterType="books">
        update ssmbuild.books
        set bookName = #{bookName},bookCounts = #{bookCounts},detail = #{detail}
        where bookID = #{bookID}
    </update>
</mapper>
```

**完成业务层对dao的调用**
```java
public interface BookService {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int delBook(int id);
    //修改一本书
    int updateBook(Books books);
    //查询一本书
    Books selectBookById(int id);
    //查询所有书
    List<Books> selectAllBooks();
}

public class BookServiceImpl implements BookService {

    //service 到用 dao层，组合Dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int delBook(int id) {
        return bookMapper.delBook(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books selectBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    public List<Books> selectAllBooks() {
        return bookMapper.selectAllBooks();
    }
}
```

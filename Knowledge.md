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
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
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

## Spring层
**注意spring的这几个配置文件要有关联(import,或者使用IDEA的功能)**  
____
**spring整合dao**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 1.关联数据库文件 -->
    <context:property-placeholder location="classpath:database.properties"></context:property-placeholder>

    <!-- 2.连接池
        dbcp: 半自动化操作，不能自动连接
        c3p0: 自动化操作(自动化的加载配置文件，并且可以自动设置到对象中)
        druid:
     -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>

    <!-- 3.sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
        <!-- 绑定Mybatis配置文件 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
    </bean>

    <!-- 配置dao接口扫描包，动态实现dao接口，并注入到Spring容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 注入SqlSessionFactory,用的是value,因为set方法传递的是字符串
            value的值是自己配置的sqlSessionFactory的id值
         -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
        <!-- 要扫描的dao包 -->
        <property name="basePackage" value="com.engulf.dao"></property>
    </bean>
</beans>
```

**spring整合service**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 1.扫描service下的包 -->
    <context:component-scan base-package="com.engulf.service"></context:component-scan>

    <!-- 2.将我们的所有业务类，注入到Spring，可以通过配置或者注解实现 -->
    <bean id="BookServiceImpl" class="com.engulf.service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"></property>
    </bean>

    <!-- 3.声明式事务配置 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入数据源 -->
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!-- 4.aop事务支持 -->

</beans>
```

## SpringMVC
**配置DispatcherServlet和乱码过滤器(web.xml)**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- DispatcherServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <!-- 导入spinrg总的配置文件 -->
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 乱码过滤 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- Session -->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

**springmvc配置文件**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 1.注解驱动 -->
    <mvc:annotation-driven></mvc:annotation-driven>
    <!-- 2.静态资源过滤 -->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
    <!-- 3.扫描包 -->
    <context:component-scan base-package="com.engulf.controller"></context:component-scan>
    <!-- 4.视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```

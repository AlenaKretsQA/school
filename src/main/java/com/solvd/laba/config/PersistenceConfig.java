package com.solvd.laba.config;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PersistenceConfig {

    @Getter
    private static final SqlSessionFactory sessionFactory;

    static {
        try (InputStream is = Resources.getResourceAsStream("mybatis/mybatis-config.xml")) {
            sessionFactory = new SqlSessionFactoryBuilder()
                    .build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

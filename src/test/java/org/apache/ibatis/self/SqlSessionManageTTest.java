package org.apache.ibatis.self;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * 作者：Administrator
 * 时间：2019/3/27
 */
public class SqlSessionManageTTest {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() throws FileNotFoundException {
        //配置文件
        InputStream configFile = new FileInputStream(
                "D:\\sourceWs\\mybatis3\\src\\test\\java\\org\\apache\\ibatis\\self\\mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        //加载配置文件得到SqlSessionFactory
        return sqlSessionFactory.openSession();
    }

    public static Test getOne(SqlSession sqlSession, int id) throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test test = testMapper.selectByPrimaryKey(id);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return test;
    }

    public static int update(SqlSession sqlSession, int id) throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        long start = System.currentTimeMillis();
        Test testParam = new Test();
        testParam.setId(id);
        testParam.setName("name");
        int i = testMapper.updateByPrimaryKeySelective(testParam);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        return i;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SqlSession sqlSession = getSqlSession();
        try {
//            diffSession();
            System.out.println(update(sqlSession, 1));
//            System.out.println(getPosts(sqlSession, 1));
//            System.out.println(insert(sqlSession, new Test(null, 66, "test insert")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}

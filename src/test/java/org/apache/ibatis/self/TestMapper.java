package org.apache.ibatis.self;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);


    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);


}
package crm.mapper;

import crm.po.BaseDict;
import crm.po.BaseDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseDictMapper {
    int countByExample(BaseDictExample example);

    int deleteByExample(BaseDictExample example);

    int deleteByPrimaryKey(String dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    List<BaseDict> selectByExample(BaseDictExample example);

    BaseDict selectByPrimaryKey(String dictId);

    int updateByExampleSelective(@Param("record") BaseDict record, @Param("example") BaseDictExample example);

    int updateByExample(@Param("record") BaseDict record, @Param("example") BaseDictExample example);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
    
    
}
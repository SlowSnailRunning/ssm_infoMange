package crm.mapper;

import java.util.List;

import crm.po.BaseDict;

public interface BaseDictMapperCustom {
	//搜索搜索框下啦列表数据
	public List<BaseDict> findDictByCode(String code) throws Exception;
}

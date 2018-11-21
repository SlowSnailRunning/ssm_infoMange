package crm.mapper;

import java.util.List;

import crm.po.Customer;
import crm.po.QueryVo;

public interface CustomerMapperCustom {
	public List<Customer> findCustomerByMsg(QueryVo queryVo) throws Exception;
	
	public int findCustomerByVoCount(QueryVo queryVo) throws Exception;
}

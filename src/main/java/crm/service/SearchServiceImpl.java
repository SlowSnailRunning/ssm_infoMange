package crm.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.mapper.BaseDictMapperCustom;
import crm.mapper.CustomerMapper;
import crm.mapper.CustomerMapperCustom;
import crm.po.BaseDict;
import crm.po.Customer;
import crm.po.QueryVo;
@Service
public class SearchServiceImpl {
	
	@Autowired
	private BaseDictMapperCustom baseDictMapperCustom;
	
	@Autowired
	private CustomerMapperCustom customerMapperCustom;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	public List<BaseDict> findDictByCodeInService(String code) throws Exception {
		
		return baseDictMapperCustom.findDictByCode(code);
	}
	
	public List<Customer> findCustomerByMsg(QueryVo queryVo) throws Exception{
		
		return customerMapperCustom.findCustomerByMsg(queryVo);
	}
	
	public int findCustomerByVoCount(QueryVo queryVo) throws Exception{
		return customerMapperCustom.findCustomerByVoCount(queryVo);
	}
	
	public Customer selectByPrimaryKey(Long id) throws Exception{
		return customerMapper.selectByPrimaryKey(id);
	}
	
	public int updateCustomer(Customer customer) throws Exception{
		return customerMapper.updateByPrimaryKeySelective(customer);
	}
	
	public int deleteCustormer(Long id) throws Exception {
		return customerMapper.deleteByPrimaryKey(id);
	}
}

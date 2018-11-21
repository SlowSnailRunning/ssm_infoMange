package crm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;
import crm.po.BaseDict;
import crm.po.Customer;
import crm.po.QueryVo;
import crm.service.SearchServiceImpl;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchServiceImpl serviceImpl;
	
	//使用常量
	@Value("${customer.dict.from}")
	private String fromCode;
	@Value("${customer.dict.industry}")
	private String industryCode;
	@Value("${customer.dict.level}")
	private String levelCode;
	
	//初始页信息
	@RequestMapping("/lists")
	//数据回显的对象key：queryVo
	public String findLists(Model model,@ModelAttribute("queryVo") QueryVo queryVo) {
		List<BaseDict> fromType=null;
		List<BaseDict> industryType=null;
		List<BaseDict> levelType=null;
		List<Customer> cusList=null;
		
		try {
			fromType=serviceImpl.findDictByCodeInService(fromCode);
			industryType=serviceImpl.findDictByCodeInService(industryCode);
			levelType=serviceImpl.findDictByCodeInService(levelCode);
			
			/*if(queryVo.getPage()==null) {
				queryVo.setPage(1);
			}*/
			//设置数据查询的起始位置
			queryVo.setStart((queryVo.getPage()-1)*queryVo.getSize());
			//表格数据
			cusList=serviceImpl.findCustomerByMsg(queryVo);
			
			int total=serviceImpl.findCustomerByVoCount(queryVo);
			Page<Customer> page = new Page<Customer>();
			page.setTotal(total);		//数据总数
			page.setSize(queryVo.getSize());	//每页显示条数
			page.setPage(queryVo.getPage()); //当前页数
			page.setRows(cusList);	//数据列表
			
			//下拉列表数据
			model.addAttribute("fromType", fromType);
			model.addAttribute("industryType",industryType);
			model.addAttribute("levelType",levelType);
			
			//填充表格数据到model
			model.addAttribute("page",page);
			
			//解决name的中文乱码
			/*if(queryVo.getCustName() != null){
				queryVo.setCustName(new String(queryVo.getCustName().getBytes("iso8859-1"), "utf-8"));
			}*/
			return "customer";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public Customer selectCustomerById(long id) throws Exception {
		Customer customer=serviceImpl.selectByPrimaryKey(id);
		return customer;
	}
	
	@RequestMapping("/updateCustomer")
	public @ResponseBody String updateCustomer(Customer customer) throws Exception {
		int i= serviceImpl.updateCustomer(customer);
		 //该方法在前台是通过ajax请求的，返回的默认是字符串（也可以是设定的返回值）；
		//return "customer";在未加入@ResponseBody时，会走到试图解析器，被解析为一个页面的字符串
		 //return "customer";
		 return i+"";
	}
	
	@RequestMapping("/deleteCustomer")
	public @ResponseBody String deleteCustomerById(Long id) throws Exception{
		int i=serviceImpl.deleteCustormer(id);
		return "delete "+i;
	}
}

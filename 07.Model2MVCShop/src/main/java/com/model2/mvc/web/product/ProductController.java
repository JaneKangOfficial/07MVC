package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



//==> ��ǰ���� Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProduct() throws Exception {

		System.out.println("/product/addProductView : GET");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product ) throws Exception {

		System.out.println("product/addProduct : POST");
		//Business Logic
		productService.addProduct(product);
		
		return "forward:/product/readProduct.jsp";
	}
	
	@RequestMapping(value="getProduct", method=RequestMethod.GET)
	public String getProduct( @RequestParam("prodNo") int prodNo , @RequestParam("menu") String menu , Model model ) throws Exception {
		
		System.out.println("product/getProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model �� View ����
		model.addAttribute("product", product);
		
		//return "forward:/product/getProduct.jsp";
		
		if(menu == null) {
//			return "forward:/product/Product.jsp?prodNo="+prodNo;
			return "forward:/product/getProduct.jsp?prodNo="+prodNo;
		}
		if(menu.equals("search")) {
			System.out.println("GetProductAction Search");
			
			return "forward:/product/updateProductView.jsp";
		}else if(menu.equals("manage")){
			System.out.println("GetProductAction Manage");
			return "forward:/product/updateProduct.jsp";
		}else {
			return "forward:/product/getProduct.jsp?prodNo="+prodNo;
		}
		
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("product/updateProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model �� View ����
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping(value="updateProduct" ,method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product , Model model ,HttpServletRequest request) throws Exception{

		System.out.println("product/updateProduct : POST");
		//Business Logic
		productService.updateProduct(product);
		
		return "forward:/product/readProduct.jsp";
		//return "redirect:/getProduct.do?prodNo="+product.getProdNo()+"&menu="+menu;
	}
	
//	@RequestMapping("/loginView.do")
//	public String loginView() throws Exception{
//		
//		System.out.println("/loginView.do");
//
//		return "redirect:/user/loginView.jsp";
//	}
	
//	@RequestMapping("/login.do")
//	public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
//		
//		System.out.println("/login.do");
//		//Business Logic
//		User dbUser=userService.getUser(user.getUserId());
//		
//		if( user.getPassword().equals(dbUser.getPassword())){
//			session.setAttribute("user", dbUser);
//		}
//		
//		return "redirect:/index.jsp";
//	}
//	
//	@RequestMapping("/logout.do")
//	public String logout(HttpSession session ) throws Exception{
//		
//		System.out.println("/logout.do");
//		
//		session.invalidate();
//		
//		return "redirect:/index.jsp";
//	}
//	
//	@RequestMapping("/checkDuplication.do")
//	public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{
//		
//		System.out.println("/checkDuplication.do");
//		//Business Logic
//		boolean result=userService.checkDuplication(userId);
//		// Model �� View ����
//		model.addAttribute("result", new Boolean(result));
//		model.addAttribute("userId", userId);
//
//		return "forward:/user/checkDuplication.jsp";
//	}
//	
	@RequestMapping(value="listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("product/listProduct : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
}

package com.waterbilling.demo.controller.web;
import org.springframework.stereotype.Controller;

import java.util.List;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterbilling.demo.dto.request.ServiceRegistrationRequest;
import com.waterbilling.demo.dto.request.SupportRequest;
import com.waterbilling.demo.dto.response.BillResponse;
import com.waterbilling.demo.model.News;
import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.service.BillService;
import com.waterbilling.demo.service.NewsService;
import com.waterbilling.demo.service.ServiceRegistrationService;
import com.waterbilling.demo.service.SupportService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller()
public class HomeController {

	@Autowired
	private SupportService supportService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private BillService billService;
	@Autowired
	private ServiceRegistrationService serviceRegistrationService;
    @Autowired
    private ModelMapper modelMapper;

	@GetMapping(value="/trang-chu")
	public void home() {
		
	}
	@GetMapping(value="/bang-gia")
	public void priceList() {
		
	}
	@GetMapping(value="/ho-tro")
	public void support(@Valid @RequestBody SupportRequest request) {
		supportService.saveSupport(modelMapper.map(request, Support.class));
	}
	


	@GetMapping(value="/tra-cuu")
	public List<BillResponse> lookup(@Valid @RequestBody Integer FacilityID) {
		return billService.findBillResponses(FacilityID);
	}
	@GetMapping(value="dang-ky-dich-vu")
	public void getMethodName(@Valid @RequestBody ServiceRegistrationRequest request) {
		serviceRegistrationService.saveServiceRegistration(modelMapper.map(request, ServiceRegistration.class));
	}
	
	
	
}
	
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//		return new ModelAndView("redirect:/trang-chu");
//	}
//	@GetMapping(value="/bang-gia")
//	public void priceList() {
//
//	}
//	@GetMapping(value="/ho-tro")
//	public void support(@Valid @RequestBody SupportRequest request) {
//		supportService.saveSupport(modelMapper.map(request, Support.class));
//	}
//
//	@GetMapping(value="/tin-tuc")
//	public List<News>news() {
//		return newsService.findNews();
//	}
//	@GetMapping(value="/tra-cuu")
//	public List<BillResponse> lookup(@Valid @RequestBody Integer FacilityID) {
//		return billService.findBillResponses(FacilityID);
//	}
//	@GetMapping(value="dang-ky-dich-vu")
//	public void getMethodName(@Valid @RequestBody ServiceRegistrationRequest request) {
//		serviceRegistrationService.saveServiceRegistration(modelMapper.map(request, ServiceRegistration.class));
//	}
//
//
//
//
//
//
////	@RequestMapping(value = "/logout", method = RequestMethod.GET)
////	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		if (auth != null) {
////			new SecurityContextLogoutHandler().logout(request, response, auth);
////		}
////		return new ModelAndView("redirect:/trang-chu");
////	}
//}

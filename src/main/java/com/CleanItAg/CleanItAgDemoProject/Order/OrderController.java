package com.CleanItAg.CleanItAgDemoProject.Order;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CleanItAg.CleanItAgDemoProject.Employee.Employee;
import com.CleanItAg.CleanItAgDemoProject.Employee.Employee.Role;
import com.CleanItAg.CleanItAgDemoProject.Employee.EmployeeRepository;
import com.CleanItAg.CleanItAgDemoProject.Order.QueryService.queryKeys;

@RestController
public class OrderController {

	private OrderRepository orderRepository;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private QueryService queryService;
	private EmployeeRepository employeeRepository;

	public OrderController(OrderRepository orderRepository, EmployeeRepository employeeRepository, QueryService queryService) {
		super();
		this.orderRepository = orderRepository;
		this.queryService = queryService;
		this.employeeRepository = employeeRepository;
	}

	
	//@PreAuthorize("hasAuthority(T(com.CleanItAg.CleanItAgDemoProject.Employee.Employee.Role).employee.toString())")... this is also not really better...
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('employee')")
	public ResponseEntity<List<OrderBeanBasicDTO>> getOrders() {
		List<OrderBeanBasicDTO> orders = orderRepository.findAll().stream().map(ob -> new OrderBeanBasicDTO(ob)).toList();
		return ResponseEntity.ok(orders);
	}
	

		@RequestMapping(value="/orders/full", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('manager')")
		public ResponseEntity<List<OrderBeanFullDTO>> getOrdersFullDetails() {
			List<OrderBeanFullDTO> orders = orderRepository.findAll().stream().map(ob -> new OrderBeanFullDTO(ob)).toList();
			return ResponseEntity.ok(orders);
		}
	
	@RequestMapping(value="/orders/id/{orderid}", method = RequestMethod.GET)
	public ResponseEntity<OrderBeanBasicDTO> getOrdersById(@PathVariable int orderid) {
		Optional<OrderBean> order = orderRepository.findById(orderid);
		if (order.isEmpty()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new OrderBeanBasicDTO(order.get()));
	}
	
	//vs GET? caching vs sensitivity of data in query string 
	@RequestMapping(value="/orders/query", method = RequestMethod.POST)
	public ResponseEntity<List<OrderBean>> getOrdersByQuery(@RequestBody SearchQuery searchQuery) {
		logger.info("Request query string: "+searchQuery);
		Dictionary<queryKeys, Optional<String>> queryDict = queryService.parse(searchQuery);
		logger.info("Request query dictionary: "+queryDict);
		// Check if queryDict is empty or consist only of empty Optionals.
		if(queryDict.isEmpty() || Collections.list(queryDict.elements()).stream()
		.filter(opt -> opt.isPresent())
		.count() == 0) return ResponseEntity.badRequest().build();
				
		// Check which keys are present and query database for orders.
		if (queryDict.get(queryKeys.customerEmail).isPresent() && queryDict.get(queryKeys.customerName).isPresent()) {
			List<OrderBean> result = orderRepository.findAllByCustomer_NameContainsAndCustomer_EmailContainsAllIgnoreCase(
					queryDict.get(queryKeys.customerName).get(), queryDict.get(queryKeys.customerEmail).get());
			return ResponseEntity.ok(result);
		}
		if (queryDict.get(queryKeys.customerName).isPresent()) {
			List<OrderBean> result = orderRepository.findAllByCustomer_NameContainsAllIgnoreCase(
					queryDict.get(queryKeys.customerName).get());
			return ResponseEntity.ok(result);
		}
		if (queryDict.get(queryKeys.customerEmail).isPresent()) {
			List<OrderBean> result = orderRepository.findAllByCustomer_EmailContainsAllIgnoreCase(
					queryDict.get(queryKeys.customerEmail).get());
			return ResponseEntity.ok(result);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(value="/orders/id/{orderid}", method = RequestMethod.PUT)
	public ResponseEntity<? extends OrderBeanDTO> updateOrderById(@PathVariable int orderid, @RequestBody OrderChangeRequest request, Authentication authentication){
		
			Employee issuer = (Employee) authentication.getPrincipal();
			logger.debug("put request by principal: "+issuer.getEmail());
			
			Optional<OrderBean> persistedOrder = orderRepository.findById(orderid);
			if (persistedOrder.isEmpty())return ResponseEntity.badRequest().build();
			
			persistedOrder.get().changeStatus(request.getNewStatus(), issuer);
			
			try {
			  orderRepository.save(persistedOrder.get());
			} catch(Exception e) {
				logger.error(e.toString());
				return ResponseEntity.internalServerError().build();
			}
		
			if(issuer.getAuthorities().contains(new SimpleGrantedAuthority(Role.manager.toString())))return ResponseEntity.ok(new OrderBeanFullDTO(persistedOrder.get()));
			return ResponseEntity.ok(new OrderBeanBasicDTO(persistedOrder.get()));
		
	}
}

package com.cleanit.Order;

import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

import com.cleanit.Order.model.*;
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

import com.cleanit.Employee.model.Employee;
import com.cleanit.Employee.model.Employee.Role;
import com.cleanit.Employee.EmployeeRepository;

@RestController
public class OrderController {

	private final OrderRepository orderRepository;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final QueryService queryService;
	private EmployeeRepository employeeRepository;

	public OrderController(OrderRepository orderRepository, EmployeeRepository employeeRepository, QueryService queryService) {
		super();
		this.orderRepository = orderRepository;
		this.queryService = queryService;
		this.employeeRepository = employeeRepository;
	}

	
	//@PreAuthorize("hasAuthority(T(com.CleanItAg.CleanItAgDemoProject.Employee.model.Employee.Role).employee.toString())")... this is also not really better...
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('employee')")
	public ResponseEntity<List<OrderBasicDto>> getOrders() {
		List<OrderBasicDto> orders = orderRepository.findAll().stream().map(OrderBasicDto::new).toList();
		return ResponseEntity.ok(orders);
	}
	

		@RequestMapping(value="/orders/full", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('manager')")
		public ResponseEntity<List<OrderFullDto>> getOrdersFullDetails() {
			List<OrderFullDto> orders = orderRepository.findAll().stream().map(OrderFullDto::new).toList();
			return ResponseEntity.ok(orders);
		}
	
	@RequestMapping(value="/orders/id/{orderid}", method = RequestMethod.GET)
	public ResponseEntity<OrderBasicDto> getOrdersById(@PathVariable int orderid) {
		Optional<OrderService> order = orderRepository.findById(orderid);
        return order.map(orderService -> ResponseEntity.ok(new OrderBasicDto(orderService))).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	//vs GET? caching vs sensitivity of data in query string 
	@RequestMapping(value="/orders/query", method = RequestMethod.POST)
	public ResponseEntity<List<OrderService>> getOrdersByQuery(@RequestBody SearchQuery searchQuery) {
		logger.info("Request query string: "+searchQuery);
		Dictionary<QueryService.queryKeys, Optional<String>> queryDict = queryService.parse(searchQuery);
		logger.info("Request query dictionary: "+queryDict);
		// Check if queryDict is empty or consist only of empty Optionals.
		if(queryDict.isEmpty() || Collections.list(queryDict.elements()).stream().noneMatch(Optional::isPresent)) return ResponseEntity.badRequest().build();
				
		// Check which keys are present and query database for orders.
		if (queryDict.get(QueryService.queryKeys.customerEmail).isPresent() && queryDict.get(QueryService.queryKeys.customerName).isPresent()) {
			List<OrderService> result = orderRepository.findAllByCustomer_NameContainsAndCustomer_EmailContainsAllIgnoreCase(
					queryDict.get(QueryService.queryKeys.customerName).get(), queryDict.get(QueryService.queryKeys.customerEmail).get());
			return ResponseEntity.ok(result);
		}
		if (queryDict.get(QueryService.queryKeys.customerName).isPresent()) {
			List<OrderService> result = orderRepository.findAllByCustomer_NameContainsAllIgnoreCase(
					queryDict.get(QueryService.queryKeys.customerName).get());
			return ResponseEntity.ok(result);
		}
		if (queryDict.get(QueryService.queryKeys.customerEmail).isPresent()) {
			List<OrderService> result = orderRepository.findAllByCustomer_EmailContainsAllIgnoreCase(
					queryDict.get(QueryService.queryKeys.customerEmail).get());
			return ResponseEntity.ok(result);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(value="/orders/id/{orderid}", method = RequestMethod.PUT)
	public ResponseEntity<? extends OrderDto> updateOrderById(@PathVariable int orderid, @RequestBody OrderChangeRequest request, Authentication authentication){
		
			Employee issuer = (Employee) authentication.getPrincipal();
			logger.debug("put request by principal: "+issuer.getEmail());
			
			Optional<OrderService> persistedOrder = orderRepository.findById(orderid);
			if (persistedOrder.isEmpty())return ResponseEntity.badRequest().build();
			
			persistedOrder.get().changeStatus(request.getNewStatus(), issuer);
			
			try {
			  orderRepository.save(persistedOrder.get());
			} catch(Exception e) {
				logger.error(e.toString());
				return ResponseEntity.internalServerError().build();
			}
		
			if(issuer.getAuthorities().contains(new SimpleGrantedAuthority(Role.manager.toString())))return ResponseEntity.ok(new OrderFullDto(persistedOrder.get()));
			return ResponseEntity.ok(new OrderBasicDto(persistedOrder.get()));
		
	}
}

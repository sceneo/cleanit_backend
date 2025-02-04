package com.cleanit.Order;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

import com.cleanit.Order.model.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

	public enum queryKeys {customerName, customerEmail};
	
	public QueryService() {
		super();
	}
	
	// Using this method as a proxy to initialize response.
	public Dictionary<queryKeys, Optional<String>> parse(SearchQuery searchQuery) {
		Dictionary<queryKeys, Optional<String>> response = new Hashtable<queryKeys, Optional<String>>();
		response.put(queryKeys.customerName, Optional.empty());
		response.put(queryKeys.customerEmail, Optional.empty());
		
		return parse(searchQuery.getQuery(), response);
	}


	private Dictionary<queryKeys, Optional<String>> parse(String querystr, Dictionary<queryKeys, Optional<String>> response) {
		if(!querystr.contains(":")) {
			if(response.get(queryKeys.customerName).isEmpty())response.put(queryKeys.customerName, Optional.of(querystr));
			return response;
		}
		int indexColon = querystr.lastIndexOf(":");
		int whitespace = querystr.lastIndexOf(" ", indexColon);
		String key=querystr.substring(whitespace+1, indexColon).strip().toLowerCase();
		String value=querystr.substring(indexColon+1).strip();

        if (key.equals("e")) {
            if (response.get(queryKeys.customerEmail).isEmpty())
                response.put(queryKeys.customerEmail, Optional.of(value));
        }
		
		
		if (whitespace < 0)querystr="";
		else querystr = querystr.substring(0, whitespace);
		
		if (!querystr.isBlank()){
			parse(querystr, response);
		}
		return response;
	}

}

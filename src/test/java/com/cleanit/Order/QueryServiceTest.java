package com.cleanit.Order;

import com.cleanit.Order.model.SearchQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryServiceTest {

    private QueryService queryService;

    @BeforeEach
    public void setUp() {
        queryService = new QueryService();
    }

    @Test
    public void testParseQueryWithCustomerName() {
        SearchQuery searchQuery = new SearchQuery("John Doe");

        Dictionary<QueryService.queryKeys, Optional<String>> result = queryService.parse(searchQuery);

        assertTrue(result.get(QueryService.queryKeys.customerName).isPresent());
        assertEquals("John Doe", result.get(QueryService.queryKeys.customerName).get());
        assertTrue(result.get(QueryService.queryKeys.customerEmail).isEmpty());
    }

    @Test
    public void testParseQueryWithCustomerEmail() {
        SearchQuery searchQuery = new SearchQuery("e:john@example.com");

        Dictionary<QueryService.queryKeys, Optional<String>> result = queryService.parse(searchQuery);

        assertTrue(result.get(QueryService.queryKeys.customerEmail).isPresent());
        assertEquals("john@example.com", result.get(QueryService.queryKeys.customerEmail).get());
        assertTrue(result.get(QueryService.queryKeys.customerName).isEmpty());
    }

    @Test
    public void testParseQueryWithCustomerNameAndEmail() {
        SearchQuery searchQuery = new SearchQuery("John Doe e:john@example.com");

        Dictionary<QueryService.queryKeys, Optional<String>> result = queryService.parse(searchQuery);

        assertTrue(result.get(QueryService.queryKeys.customerName).isPresent());
        assertEquals("John Doe", result.get(QueryService.queryKeys.customerName).get());
        assertTrue(result.get(QueryService.queryKeys.customerEmail).isPresent());
        assertEquals("john@example.com", result.get(QueryService.queryKeys.customerEmail).get());
    }

    @Test
    public void testParseQueryWithNoMatchingKeys() {
        SearchQuery searchQuery = new SearchQuery("random text without key");

        Dictionary<QueryService.queryKeys, Optional<String>> result = queryService.parse(searchQuery);

        assertTrue(result.get(QueryService.queryKeys.customerName).isPresent());
        assertEquals("random text without key", result.get(QueryService.queryKeys.customerName).get());
        assertTrue(result.get(QueryService.queryKeys.customerEmail).isEmpty());
    }
}

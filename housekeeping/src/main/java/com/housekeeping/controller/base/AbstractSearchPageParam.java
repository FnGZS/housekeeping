package com.housekeeping.controller.base;

import java.util.Map;

/**
 * @author
 */
public class AbstractSearchPageParam extends AbstractOrderPageParam {
	
	private Map<String, String> searchMap;

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
}

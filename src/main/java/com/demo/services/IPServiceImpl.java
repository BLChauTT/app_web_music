package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IPServiceImpl implements IPService {

	private List<String> blockIPs;

	public IPServiceImpl() {
		blockIPs = new ArrayList<String>();
		blockIPs.add("172.16.10.3");
	}

	@Override
	public boolean valid(String ip) {
		for (String blockIP : blockIPs) {
			if (blockIP.equalsIgnoreCase(ip)) {
				return false;
			}
		}
		return true;
	}

}

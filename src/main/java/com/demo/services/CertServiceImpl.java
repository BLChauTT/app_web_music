package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Cert;

@Service
public class CertServiceImpl implements CertService {

	@Override
	public List<Cert> findAll() {
		List<Cert> certs = new ArrayList<Cert>();
		certs.add(new Cert(1, "Cert 1"));
		certs.add(new Cert(2, "Cert 2"));
		certs.add(new Cert(3, "Cert 3"));
		certs.add(new Cert(4, "Cert 4"));
		certs.add(new Cert(5, "Cert 5"));
		return certs;
	}

}

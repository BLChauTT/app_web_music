package com.demo.controllers;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo10")
public class Demo10Controller { 

	@Autowired
	private MessageSource messageSourceLocale;

	@GetMapping({ "", "index", "/" })
	public String index(Locale locale, ModelMap modelMap) {
		System.out.println("country: " + locale.getCountry());
		System.out.println("display country: " + locale.getDisplayCountry());
		System.out.println("language: " + locale.getLanguage());
		System.out.println("display language: " + locale.getDisplayLanguage());

		int price = 1234567;
		NumberFormat numberFormat1 = NumberFormat.getCurrencyInstance(locale);
		System.out.println("price: " + numberFormat1.format(price));

		int quantity = 1234567;
		NumberFormat numberFormat2 = NumberFormat.getNumberInstance(locale);
		System.out.println("quantity: " + numberFormat2.format(quantity));

		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
		System.out.println("date: " + dateFormat.format(new Date()));

		String msg = messageSourceLocale.getMessage("msg", null, locale);
		System.out.println(msg);

		String msg2 = messageSourceLocale.getMessage("msg2", new Object[] { "Iphone 15" }, locale);
		System.out.println(msg2);
		
		modelMap.put("price", price);
		modelMap.put("quantity", quantity);
		modelMap.put("today", new Date());
		
		return "demo10/index";
	}

}

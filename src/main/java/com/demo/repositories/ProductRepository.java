package com.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entitiesjpa.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("from Product where name like %:keyword%")
	public List<Product> findByKeyword(@Param("keyword") String keyword);
	
	@Query("from Product where price >= :min and price <= :max")
	public List<Product> findByPrices(@Param("min") double min, @Param("max") double max);
	
	@Query("from Product where year(created) = :year")
	public List<Product> findByYear(@Param("year") int year);
	
	@Query("from Product where created >= :start and created <= :end")
	public List<Product> findByDates(@Param("start") Date start, @Param("end") Date end);
	
	@Query("from Product where category.id = :categoryId")
	public List<Product> findByCategoryId(@Param("categoryId") int categoryId);
	
	@Query("from Product order by price desc")
	public List<Product> sort1();
	
	@Query("from Product where status = :status order by price desc")
	public List<Product> sort2(@Param("status") boolean status);
	
	@Query(value = "select * from product limit :n", nativeQuery = true)
	public List<Product> limit1(@Param("n") int n);
	
	@Query(value = "select * from product limit :start, :n", nativeQuery = true)
	public List<Product> limit2(@Param("start") int start, @Param("n") int n);
	
	@Query(value = "select * from product where status = :status order by id desc limit :start, :n", nativeQuery = true)
	public List<Product> limit3(@Param("status") boolean status, @Param("start") int start, @Param("n") int n);
	
	@Query("select sum(quantity) from Product")
	public long sum1();
	
	@Query("select sum(quantity) from Product where status = :status")
	public long sum2(@Param("status") boolean status);
	
	@Query("select sum(price * quantity) from Product where status = :status")
	public double sum3(@Param("status") boolean status);
	
	@Query("select count(id) from Product where status = :status")
	public long count2(@Param("status") boolean status);
	
	@Query("select min(price) from Product")
	public double min1();
	
	@Query("select min(price) from Product where status = :status")
	public double min2(@Param("status") boolean status);
	
	@Query("select max(price) from Product")
	public double max1();
	
	@Query("select max(price) from Product where status = :status")
	public double max2(@Param("status") boolean status);
	
	@Query("select avg(price) from Product")
	public double avg1();
	
	@Query("select avg(price) from Product where status = :status")
	public double avg2(@Param("status") boolean status);
	
}

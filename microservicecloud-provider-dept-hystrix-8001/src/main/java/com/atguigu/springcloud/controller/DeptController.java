package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service = null;

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	//一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "test")
	public Dept get(@PathVariable("id") Long id)
	{

		Dept dept = this.service.get(id);
		System.out.println(dept);

		if (id>10) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}

		return dept;
	}

//	public Dept processHystrix_Get(@PathVariable("id") Long id)
//	{
//		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
//				.setDb_source("no this database in MySQL");
//	}
	public Dept test(@PathVariable("id") Long id){
		Dept d= new Dept();
		d.setDname("熔断错误");
		d.setDeptno(id);
		d.setDb_source("kong");
		return d;
	}
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}
}
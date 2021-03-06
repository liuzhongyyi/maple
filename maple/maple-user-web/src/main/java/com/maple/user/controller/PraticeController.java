package com.maple.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maple.pojo.Pratice;
import com.maple.service.PraticeService;
import com.maple.service.UserService;
import entity.PageResult;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/pratice")
public class PraticeController {

	@Reference
	private PraticeService praticeService;
	@Reference
	private UserService userService;
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Pratice> findAll(){			
		return praticeService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return praticeService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param pratice
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Pratice pratice){
		try {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			String  id = userService.findoneByuserid(name).getUserid();
			pratice.setUserid(id);
			praticeService.add(pratice);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param pratice
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Pratice pratice){
		try {
			praticeService.update(pratice);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Pratice findOne(Long id){
		return praticeService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			praticeService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Pratice pratice, int page, int rows){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return praticeService.findPage(pratice, page, rows,name);
	}
	
}

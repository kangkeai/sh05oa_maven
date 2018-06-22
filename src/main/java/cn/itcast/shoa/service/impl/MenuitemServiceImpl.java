package cn.itcast.shoa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.shoa.dao.MenuitemDao;
import cn.itcast.shoa.domain.menuitem.Menuitem;
import cn.itcast.shoa.service.MenuitemService;

@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	@Override
	public Collection<Menuitem> getAllMenuitem() {
		return this.menuitemDao.getAllEntry();
	}
}

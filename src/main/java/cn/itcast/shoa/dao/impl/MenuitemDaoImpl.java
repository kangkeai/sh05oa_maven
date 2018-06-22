package cn.itcast.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.shoa.dao.MenuitemDao;
import cn.itcast.shoa.dao.base.impl.BaseDaoImpl;
import cn.itcast.shoa.domain.menuitem.Menuitem;

@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao{

}

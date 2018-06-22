package cn.itcast.shoa.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface BaseDao<E>{
	/**
	 * 得到E代表的所有的实体对象
	 * @return
	 */
	public Collection<E> getAllEntry();
	/**
	 * Serializable该类型可以接受所有的基本类型和String类型
	 * @param id
	 * @return
	 */
	public E getEntryById(Serializable id);
	
	public void saveEntry(E e);
	
	public void deleteEntry(Serializable id);
	
	public void updateEntry(E e);
	
	/**
	 * 根据一个或者一个以上的id，返回一个Set<E>
	 */
	public Set<E> getEntrysByIDS(Serializable[] ids);
	
	
	public Set<E> getEntrysByIDS(String ids);
	
	public E getEntryByCondition(final String entityName,final String... objects);
}

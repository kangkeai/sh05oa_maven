package cn.itcast.shoa.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.itcast.shoa.dao.base.BaseDao;

public class BaseDaoImpl<E> implements BaseDao<E>{
	
	private Class classt;
	
	/**
	 * 获取到E代表的持久化类的数据字典--->当前的实体bean的标示符的名称
	 */
	private ClassMetadata classMetadata;
	
	public BaseDaoImpl(){
		/**
		 * 1、this可以代表子类或者本类
		 * 2、不能把BaseDaoImpl在spring容器中实例化，因为如果在spring容器中实例化，则就不是泛型了
		 * 3、所以根据以上两点可以得出this应该代表子类
		 * 4、this.getClass().getGenericSuperclass()代表当前的类：就是泛型
		 * 5、注意：不能把BaseDaoImpl让spring容器实例化
		 */
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		//因为将来E代表的是某一个持久化类，而该类型是class
		this.classt = (Class)type.getActualTypeArguments()[0];
	}
	
	/**
	 *  当调用完成构造函数之后，立刻执行该init方法
	 */
	@PostConstruct
	public void init(){
		this.classMetadata = this.hibernateTemplate.getSessionFactory().getClassMetadata(this.classt);
	}
	
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

	@Override
	public Collection<E> getAllEntry() {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from "+this.classt.getName());
	}
	@Override
	public E getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		/**
		 * classMetadata是持久化类的数据字典
		 */

		return (E)this.hibernateTemplate.
					find("from "+this.classt.getName()
							+
							" where "
							+classMetadata.getIdentifierPropertyName()
							+"=?",
							id).get(0);
	}
	@Override
	public void saveEntry(E e) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(e);
	}
	@Override
	public void deleteEntry(Serializable id) {
		// TODO Auto-generated method stub
		E e = this.getEntryById(id);
		this.hibernateTemplate.delete(e);
	}
	@Override
	public void updateEntry(E e) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(e);
	}
	@Override
	public Set<E> getEntrysByIDS(Serializable[] ids) {
		/**
		 * 1、如果数组只有一个元素
		 * 2、如果数组中有两个或者两个以上的元素
		 *    from Person where pid in(1,2,3,4)
		 */
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getName());//this.classt.getName()持久化类的名字
		stringBuffer.append(" where "+this.classMetadata.getIdentifierPropertyName());
		stringBuffer.append(" in (");
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){
				stringBuffer.append(ids[i]);
			}else{
				stringBuffer.append(ids[i]+",");
			}
		}
		stringBuffer.append(")");
		List<E> list = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<E>(list);
	}

	@Override
	public Set<E> getEntrysByIDS(String ids) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getName());
		stringBuffer.append(" where "+this.classMetadata.getIdentifierPropertyName());
		stringBuffer.append(" in(");
		stringBuffer.append(ids);
		stringBuffer.append(")");
		List<E> list = this.hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<E>(list);
	}

	@Override
	public E getEntryByCondition(final String hql,final String... objects) {
		return this.hibernateTemplate.execute(new HibernateCallback<E>() {
			@Override
			public E doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				int index = 0;
				for(String s:objects){
					query.setParameter(index, s);
					index++;
				}
				return (E)query.uniqueResult();
			}
		});
	}
}

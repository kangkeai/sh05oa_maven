package cn.itcast.shoa.struts.action.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<E> extends ActionSupport implements ModelDriven<E>{
	
	private Class classt;
	private E e;
	
	public BaseAction(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		this.classt = (Class)type.getActualTypeArguments()[0];
		try {
			this.e = (E)this.classt.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public E getModel() {
		// TODO Auto-generated method stub
		return this.e;
	}
	
	
	public static final String LISTACTION = "listAction";
	
	/**
	 * 跳转了列表页面
	 */
	public static String listAction = LISTACTION;
	
	public static final String UPDATE_UI = "updateUI";
	
	/*
	 * 跳转到修改界面
	 */
	public static String updateUI = UPDATE_UI;
	
	public static final String ADD_UI = "addUI";
	
	/**
	 * 跳转到添加的页面
	 */
	public static String addUI = ADD_UI;
	
	
	public static final String ACTION2ACTION = "action2action";
	
	/**
	 * 由action跳转到action
	 */
	public static String action2action = ACTION2ACTION;
}

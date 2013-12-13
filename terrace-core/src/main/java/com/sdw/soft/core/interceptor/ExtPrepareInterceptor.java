package com.sdw.soft.core.interceptor;

import java.lang.reflect.InvocationTargetException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.interceptor.PrefixMethodInvocationUtil;

/**
 * @author syd
 * @Date 2013年12月13日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * 修改标准的PrepareInterceptor: 先执行prepare再执行相关的prepareXXX方法
 * @see com.opensymphony.xwork2.interceptor.PrepareInterceptor
 */
public class ExtPrepareInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -925371515987597285L;

	private final static String PREPARE_PREFIX = "prepare";
	private boolean alwaysInvokePrepare = true;
	
	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if(action instanceof Preparable){
			if(alwaysInvokePrepare){
				((Preparable)action).prepare();
			}
			try {
				String[] prefixes = new String[]{PREPARE_PREFIX};
				PrefixMethodInvocationUtil.invokePrefixMethod(invocation, prefixes);
			} catch (InvocationTargetException e) {
				/*
                 * The invoked method threw an exception and reflection wrapped it
                 * in an InvocationTargetException.
                 * If possible re-throw the original exception so that normal
                 * exception handling will take place.
                 */
				Throwable cause = e.getCause();
				if(cause instanceof Exception){
					throw (Exception)cause;
				}else if(cause instanceof Error){
					throw (Error)cause;
				}else{
					/*
                     * The cause is not an Exception or Error (must be Throwable) so
                     * just re-throw the wrapped exception.
                     */
					throw e;
				}
			}
		}
		return invocation.invoke();
	}
	/**
	 * Sets if the <code>preapare</code> method should always be executed.
	 * Default is <tt>true</tt>.
	 * @param alwaysInvokePrepare if <code>prepare</code> should always be executed or not.
	 */
	public void setAlwaysInvokePrepare(String alwaysInvokePrepare) {
		this.alwaysInvokePrepare = Boolean.parseBoolean(alwaysInvokePrepare);
	}

}

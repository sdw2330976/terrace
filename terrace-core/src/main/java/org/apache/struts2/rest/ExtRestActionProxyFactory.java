package org.apache.struts2.rest;

import java.util.Map;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionProxyFactory;
import com.opensymphony.xwork2.inject.Inject;

/**
 * @author syd
 * @Date 2013年12月12日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class ExtRestActionProxyFactory extends DefaultActionProxyFactory {
	
	public static final String STRUTS_REST_NAMESPACE = "struts.rest.namespace";
	
	protected String namespace;
	
	@Inject(value = STRUTS_REST_NAMESPACE,required = false)
	public void setNamespace(String namespace){
		this.namespace = namespace;
	}
	
	@Override
	public ActionProxy createActionProxy(String namespace, String actionName,
			String methodName, Map<String, Object> extraContext,
			boolean executeResult, boolean cleanupContext) {
		if(this.namespace == null || namespace.startsWith(this.namespace)){
			RestActionInvocation inv = new RestActionInvocation(extraContext,true);
			container.inject(inv);
			HttpContentTypeHandlerManager contentTypeHandlerManager = new HttpContentTypeHandlerManager();
			container.inject(contentTypeHandlerManager);
			inv.setMimeTypeHandlerSelector(contentTypeHandlerManager);
			return createActionProxy(inv,namespace, actionName, methodName, executeResult,cleanupContext);
		}else{
			return super.createActionProxy(namespace, actionName, methodName, extraContext,
					executeResult, cleanupContext);
		}
	}
}

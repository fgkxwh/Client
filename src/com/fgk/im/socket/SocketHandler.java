package com.fgk.im.socket;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import com.fgk.im.bean.Data;
import com.fgk.im.util.AppCache;


public class SocketHandler implements IoHandler {
	
	public static IoSession session;
	
	@Override
	public void exceptionCaught(IoSession session, Throwable throwable) throws Exception {
		System.out.println(session + " , "+throwable.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		System.out.println("messageReceived:"+ session + "  :"+message);
		
		if (message instanceof Data) {
			
			 Data data = (Data) message;//转换为Data对象
			
			 String uri = data.getUri();
		     String[] arrUri = uri.split("/");//以/分割开 类名和 方法名
		     String strClass = arrUri[0];//类名
		     String strMethod = arrUri[1];//方法名
		     List<Object> params = data.getParams();//参数列表
		     System.out.println("params :"+params);
//		     params.add(session);//在参数列表后 加上session

		     
			 Object service = AppCache.context.getBean(strClass);
			 Method m = service.getClass().getDeclaredMethod(strMethod,params.getClass());
		 	 Object result = m.invoke(service, params);//反射调用方法
			   
		 	 
			 //如果返回不为空   void返回类型的函数 会返回 Void Void和null不相同
//			 if (result != null) {
//					
//					List<Object> returnParams = new ArrayList<Object>();
//				
//					returnParams.add(result);
//					
//					MessageSend.send(new Data(data.getUri(),returnParams));
//				}
		}
		
	}

	
//	private Class<?>[] getClasses(List<Object> params) {
//		
//			Class<?>[] classArray = new Class<?>[params.size()];
//
//			for (int i = 0; i < params.size(); i++) {
//				classArray[i] = params.get(i).getClass();
//			}
//			return classArray;
//	}
	
	@Override
	public void messageSent(IoSession session, Object object) throws Exception {
		System.out.println(" messageSent  "+session + " , "+object);
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		System.out.println("sessionClosed"+ "  , "+session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated"+ "  , "+session);
	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus idleStatus) throws Exception {
		System.out.println("sessionIdle"+ "  , "+ idleStatus.toString());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
		this.session = session;//设置session 以供其他的类引用
	}

}

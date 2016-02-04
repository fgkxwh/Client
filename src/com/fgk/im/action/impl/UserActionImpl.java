package com.fgk.im.action.impl;

import java.util.ArrayList;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.fgk.im.action.IUserAction;
import com.fgk.im.bean.Data;
import com.fgk.im.socket.MessageSend;

@Service("UserAction")
public class UserActionImpl  implements IUserAction{
	
	@Override
	public void login(ArrayList<Object> params){
		
		for (Object object : params) {
			
			System.out.println(object);
		}
		
		MessageSend.send(new Data("UserService/login",params));
		
	}

}

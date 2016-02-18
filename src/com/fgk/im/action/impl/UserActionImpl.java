package com.fgk.im.action.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.fgk.im.action.IUserAction;
import com.fgk.im.bean.Data;
import com.fgk.im.gui.IMList;
import com.fgk.im.gui.Login;
import com.fgk.im.socket.MessageSend;

@Service("userAction")
public class UserActionImpl  implements IUserAction{
	
	@Override
	public void login(HashMap<String, Object> params){
		
		System.out.println("login success");
		new IMList();//进入好友列表界面
	}
	
	@Override
	public void register(HashMap<String, Object> params){
		
		boolean flag = (boolean) params.get("success");
		
		if (flag) {//如果注册成功

			new Login();
		}else {
			
			System.out.println("注册失败");
			return; 
		}
		
	}

}

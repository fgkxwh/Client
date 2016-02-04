package com.fgk.im.socket;

import java.util.ArrayList;
import java.util.List;

import com.fgk.im.bean.Data;
import com.google.gson.Gson;

/**
 * 信息发送
 * @author Administrator
 *
 */
public class MessageSend {
	
	public static void send(Data data){

		if (SocketHandler.session != null) {
			
			SocketHandler.session.write(data);
		}else {
			
			System.out.println(" MessageSend.send:"+"发送时session为空，数据发送失败");
		}
	}

}

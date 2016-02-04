package com.fgk.im.socket;


import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 连接服务器
 */
@Service("socketConnect")
public class SocketConnect extends Thread 
{
	private static Logger logger = Logger.getLogger(SocketConnect.class);

	//在初始化之前会执行其注解的方法
	@PostConstruct
	public void startThis() 
	{
		start();
	}

	public void run() 
	{
		IoConnector conn = new NioSocketConnector();
		conn.getFilterChain().addLast("codeFilter", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		conn.setHandler(new SocketHandler());
		conn.connect(new InetSocketAddress("192.168.1.76", 8899));
	}
}

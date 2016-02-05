package com.fgk.im;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.serialization.ObjectSerializationOutputStream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fgk.im.gui.Login;
import com.fgk.im.util.AppCache;
import com.google.gson.Gson;

public class AppRun {

	private static final Logger LOGGER = Logger.getLogger(AppRun.class);
	public static void main(String[] args) {
		
		final ApplicationContext context = new FileSystemXmlApplicationContext("conf/applicationContext.xml");
		AppCache.context = context;
		
		new Login();
		LOGGER.info("ClientApp start success");
	}
}

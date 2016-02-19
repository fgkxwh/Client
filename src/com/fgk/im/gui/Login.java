package com.fgk.im.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import org.hibernate.classic.Validatable;

import com.fgk.im.bean.Data;
import com.fgk.im.socket.MessageSend;
import com.fgk.im.socket.SocketHandler;
import com.fgk.im.util.MyValidate;
import com.sun.java.swing.plaf.windows.resources.windows;

import junit.framework.Assert;
import junit.framework.Test;

/**
 * 登录界面
 * @author Administrator
 *
 */
public class Login implements IGui{
	
	private HashMap<String, Object> params;
	private JFrame frame;
	private JPanel panel;
	private JButton buttonConfirm;//确定按钮
	private JLabel labelUserName;//用户名标签
	private JLabel labelPwd;//密码标签
	private JButton buttonCancel;//取消按钮
	private JTextArea textAreaUsername;//用户名输入框
	private JPasswordField passwordFieldPwd;//密码输入框
	private JButton buttonRegister;//注册按钮
	
	public Login(){
		
		initComponents();//界面初始化
		setEvent();//组件设置监听事件
	}
	
	
	
	@Override
	public void initComponents() {

		frame = new JFrame("登录");
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setVisible(true);
		
		buttonConfirm =  new JButton("确定");
		buttonCancel = new JButton("取消");
		buttonRegister = new JButton("注册");
		
		labelUserName = new JLabel("用户名");
		labelPwd = new JLabel("密码");
		
		textAreaUsername = new JTextArea();
		textAreaUsername.setColumns(10);
		passwordFieldPwd = new JPasswordField();
		passwordFieldPwd.setColumns(10);
		
		panel.add(labelUserName);
		panel.add(textAreaUsername);
		panel.add(labelPwd);
		panel.add(passwordFieldPwd);
		panel.add(buttonConfirm);
		panel.add(buttonCancel);
		panel.add(buttonRegister);
		
		//布局,简单的流式布局
		panel.setLayout(new FlowLayout());
		
		frame.add(panel);
		
	}

	@Override
	public void setEvent() {
		
		//确定按钮单击事件
				buttonConfirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						_buttonConfirmActionPerformed(e);
					}
				});
				
				//取消按钮单击事件
				buttonCancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						_buttonCancelActionPerformed(e);
					}
				});
				
				//注册按钮单击事件
				buttonRegister.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						_buttonRegisterActionPerformed(e);
					}
				});
		
	}
	
	@Override
	public void show() {
		initComponents();
		setEvent();
		
	}
	
	/**
	 * 确定按钮  buttonConfirm的事件监听处理函数
	 * @param event
	 */
	private void _buttonConfirmActionPerformed(ActionEvent event){
		
		String strUsername =  this.textAreaUsername.getText();//用户输入的用户名
		String strPassword = this.passwordFieldPwd.getText();//用户输入的密码
		
		params = _getHMInstance();
		params.put("strUsername", strUsername);
		params.put("strPassword",strPassword);
		
		if (MyValidate.isNullOrEmpty(strUsername,strPassword)) {//输入的用户名或者密码为空
			
			System.out.println("输入的用户名或者密码为空");
			return;
			
		}else{//输入的用户名或者密码都不为空
			
			MessageSend.send("userService/login",params);
		}
		
	}
	
	/**
	 * 取消按钮 buttonCancel的事件监听处理函数
	 * @param event
	 */
	private void _buttonCancelActionPerformed(ActionEvent event){
		
		System.exit(0);;//退出此界面
	}
	
	/**
	 * 注册按钮 buttonCancel的事件监听处理函数
	 * @param event
	 */
	private void _buttonRegisterActionPerformed(ActionEvent event){
		
		new Register();//调用注册界面
	}
	
	
	private HashMap<String, Object> _getHMInstance(){
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		if (params.isEmpty()) {
			return params;
		}else{
			params.clear();//清空hashmap
			return params;
		}
	}
	
	//界面测试主函数
	public static void main(String[] args){
		
		new Login().show();
	}

	
}

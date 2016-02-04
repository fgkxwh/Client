package com.fgk.im.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fgk.im.bean.Data;
import com.fgk.im.socket.MessageSend;
import com.fgk.im.socket.SocketHandler;

public class Login{
	
	private JFrame frame;
	private JPanel panel;
	private JButton buttonConfirm;//确定按钮
	private JLabel labelUserName;//用户名标签
	private JLabel labelPwd;//密码标签
	private JButton buttonCancel;//取消按钮
	private JTextArea textAreaUsername;//用户名输入框
	private JTextArea textAreaPwd;//密码输入框
	
	public Login(){
		
		_initComponents();//界面初始化
		_setEvent();//组件设置监听事件
	}
	
	private void _initComponents(){
		
		frame = new JFrame("Login");
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setVisible(true);
		
		buttonConfirm =  new JButton("Login");
		
		labelUserName = new JLabel("default");
		
		panel.add(buttonConfirm);
		panel.add(labelUserName);
		
		frame.add(panel);
	}
	
	private void _setEvent(){
		
		//login 按钮单击事件
		buttonConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_buttonConfirmActionPerformed(e);
			}
		});
	}
	
	/**
	 * button的事件监听处理函数
	 * @param event
	 */
	private void _buttonConfirmActionPerformed(ActionEvent event){
		
		List<Object> params = new ArrayList<Object>();
		params.add("admin");
		params.add("123456");
		
		if (SocketHandler.session !=null) {
			
			MessageSend.send(new Data("UserService/login",params));
		}else {
			
			System.out.println("SocketHandler.session 为空，数据发送失败");
		}
	}
	
	
	//界面测试主函数
	public static void main(String[] args){
		
		new Login();
	}
	
	public void start(){
		new Login();
	}
	
}

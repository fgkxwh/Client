package com.fgk.im.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.fgk.im.socket.MessageSend;
import com.fgk.im.util.TimeUtil;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月19日 上午10:05:48 
* 一对一聊天界面 
*/
public class PrivateTalk implements IGui {
	
	private String username;
	private JFrame fram;
	private JPanel panel;
	private JTextArea textAreaTalkInfo;//聊天消息记录框
	private JTextArea textAreaInput;//消息输入框
	private JButton buttonSend;//发送按钮
	
	public PrivateTalk() {
//		initComponents();
//		setEvent();
	}

	@Override
	public void initComponents() {
		
		fram = new JFrame("你和"+"<"+username+">"+"正在聊天");
		fram.setSize(600, 600);
		fram.setVisible(true);
		
		textAreaTalkInfo = new JTextArea();
		textAreaTalkInfo.setColumns(50);
		textAreaTalkInfo.setRows(20);
		textAreaTalkInfo.setBorder(new LineBorder(Color.black));
		
		textAreaInput = new JTextArea();
		textAreaInput.setColumns(50);
		textAreaInput.setRows(8);
		textAreaInput.setBorder(new LineBorder(Color.black));
		
		buttonSend = new JButton("发送");
		
		panel = new JPanel();
		panel.add(textAreaTalkInfo);
		panel.add(textAreaInput);
		panel.add(buttonSend);
		
		//布局,简单的流式布局
		panel.setLayout(new FlowLayout());
		
		fram.add(panel);
		
	}

	@Override
	public void setEvent() {
		
		//发送按钮事件
		buttonSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_buttonSendActionPerformed(e);
			}
		});
		
	}

	//发送按钮处理事件
	private void _buttonSendActionPerformed(ActionEvent event){
		
		//添加信息到聊天记录框
		this.textAreaTalkInfo.append(TimeUtil.getDate() +" <"+ getUsername() +"> \n" +textAreaInput.getText() + "\n");
		
		HashMap<String, Object> params = _getHMInstance();
		params.put("message", textAreaInput.getText());
		params.put("username",getUsername());
		
		MessageSend.send("talkService/privateTalk", params);
	}
	
	@Override
	public void show() {
		
		initComponents();
		setEvent();
	}

	public void setUsername(String username){
		
		this.username = username;
	}
	public String getUsername(){
		
		return this.username;
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
	
	public void addTalkInfo(String talkInfo){
		
		this.textAreaTalkInfo.append(talkInfo + "\n");
	}
	
	public static void main(String[] args) {
		
		new PrivateTalk().show();

	}
}

package com.fgk.im.action.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fgk.im.action.ITalkAction;
import com.fgk.im.util.AppCache;
import com.fgk.im.util.TimeUtil;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月19日 上午10:36:28 
* 聊天动作类 
*/
@Service("talkAction")
public class TalkActionImpl implements ITalkAction {

	@Override
	public void privateTalk(HashMap<String, Object> params) {
		
		String message = params.get("message").toString();
		String username = params.get("username").toString();
		
		AppCache.privateTalk.addTalkInfo(TimeUtil.getDate() +" <" +username +">\n"+ message+"\n");
	}

}

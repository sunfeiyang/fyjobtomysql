package com.sunfy.fyjobtomysql.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.sunfy.fyjobtomysql.utils.CheckUtil;
import com.sunfy.fyjobtomysql.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/weixin/wx.do")
public class weixinServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(weixinServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");//微信加密签名
        logger.info("---signature=微信加密签名---"+signature);
        String timestamp = req.getParameter("timestamp");//时间戳
        logger.info("---timestamp=时间戳---"+timestamp);
        String nonce = req.getParameter("nonce");//随机数
        logger.info("---nonce=随机数---"+nonce);
        String echostr = req.getParameter("echostr");//随机字符串
        logger.info("---echostr=随机字符串---"+echostr);

        PrintWriter out = resp.getWriter();
        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置字符串格式
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //实例化返回数据的对象
        PrintWriter out = resp.getWriter();
        //接收传回来的消息
        try {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String toUserName = map.get("ToUserName");//开发者微信号
            String fromUserName = map.get("FromUserName");//获得发送方消息
            String createTime = map.get("CreateTime");//消息创建时间 （整型）
            String MsgTypemi = CheckUtil.getSha1("MsgType");
            String mMsgType = map.get("MsgType");//text
            String content = map.get("Content");//文本消息内容
            String msgId = map.get("MsgId");//消息id，64位整型
            String picUrl = map.get("PicUrl");//图片链接（由系统生成）
            String mediaId = map.get("MediaId");//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
            String message = null;
            //判断返回消息是否是文本消息，是文本消息则进行返回
            logger.info("---客户端发送数据类型=mMsgType---"+mMsgType);
            if(MessageUtil.MESSAGE_TEXT.equals(mMsgType)){
//				TextMessage text = new TextMessage();
//				text.setFromUserName(toUserName);
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//
//				Date d = new Date();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		        String dateNowStr = sdf.format(d);
//				text.setCreateTime(dateNowStr);
//				text.setContent("您发送的消息是:"+content);
//				message = MessageUtil.textMessageToXml(text);
//				System.out.println("message--"+message);

                if("1".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenuText());
                }else if("2".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenuText());
                }else if("?".equals(content) || "？".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else{
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
                }

            }else if(MessageUtil.MESSAGE_EVENT.equals(mMsgType)){//判断是否是事件推送
                //判断事件推送的类型
                String eventType = map.get("Event");
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){//关注
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(eventType)){//取消关注
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
                }
            }else if(MessageUtil.MESSAGE_IMAGE.equals(mMsgType)){//图片
                message = MessageUtil.initImage(toUserName, fromUserName,picUrl,mediaId,msgId);
            }else if(MessageUtil.MESSAGE_VOICE.equals(mMsgType)){//语音
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
            }else if(MessageUtil.MESSAGE_VIDEO.equals(mMsgType)){//视频
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
            }else if(MessageUtil.MESSAGE_LINK.equals(mMsgType)){//链接消息
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
            }else if(MessageUtil.MESSAGE_LOCATION.equals(mMsgType)){//地理位置消息
                message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherMenuText(content));
            }
            out.print("服务器返回信息");
            out.print(message);

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
//            out.print("服务器返回信息111");
            out.close();
        }
    }
}


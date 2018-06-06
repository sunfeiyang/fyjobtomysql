package com.sunfy.fyjobtomysql.utils;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;
import com.sunfy.fyjobtomysql.po.ImageMessage;
import com.sunfy.fyjobtomysql.po.TextMessage;
import com.sunfy.fyjobtomysql.service.WXEformService;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageUtil {

    @Autowired
    private WXEformService wxEformService;

    public static MessageUtil messageUtil;

    @PostConstruct
    public void init() {
        messageUtil = this;
    }

    private final static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    public static final String MESSAGE_TEXT = "text";//文本
    public static final String MESSAGE_IMAGE = "image";//图片
    public static final String MESSAGE_VOICE = "voice";//语音
    public static final String MESSAGE_VIDEO = "video";//视频消息
    public static final String MESSAGE_LINK = "link";//链接消息
    public static final String MESSAGE_LOCATION = "location";//地理位置消息
    public static final String MESSAGE_EVENT = "event";//事件推送
    public static final String MESSAGE_SUBSCRIBE = "subscribe";//关注
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";//取消关注
    public static final String MESSAGE_CLICK = "CLICK";//菜单点击
    public static final String MESSAGE_VIEW = "VIEW";//菜单点击

    /**
     * 将xml转换为map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        //从request中获取输入流
        InputStream ins = request.getInputStream();
        //read对象中获取数据
        Document doc = reader.read(ins);
//        System.out.println(doc);
        //获取xml中的根元素
        Element root = doc.getRootElement();
//        System.out.println(root);
        //获取根元素的所有支点，并放进list中
        List<Element> list = root.elements();
//        System.out.println("---"+list);
        //for遍历 并将元素放入集合中
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
//        System.out.println(map.toString());
        return map;
    }

    /**
     * 将文本对象消息转换为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        //实例化一个转化xml的对象
        XStream xstream = new XStream();
        xstream.alias("xml",textMessage.getClass());
        logger.info("返回用户的xml文件---");
        System.out.println(xstream.toXML(textMessage));
        return xstream.toXML(textMessage);
    }

    /**
     * 将图片对象消息转换为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        //实例化一个转化xml的对象
        System.out.println("进入图片信息22221------");
        XStream xstream = new XStream();
        xstream.alias("xml",imageMessage.getClass());
        System.out.println(xstream.toXML(imageMessage));
        return xstream.toXML(imageMessage);
    }

    /**
     * 文本信息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName,String fromUserName,String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        text.setCreateTime(dateNowStr);
        //将内容写入数据库后再返回
        Eform_NewEvent eform_newEvent = new Eform_NewEvent();
        if (!content.isEmpty()) {
            eform_newEvent.setContent(content);
            eform_newEvent.setOrgCode("sfy");
            eform_newEvent.setUsername("孙斐扬");
            eform_newEvent.setUsercode("sunfy");
            eform_newEvent.setCretionDate(d);
            messageUtil.wxEformService.WXNewEvent(eform_newEvent);
            logger.info("微信订阅号写入数据库成功！");
            String wxContent = "事项 “"+content+"” 添加成功！";
            text.setContent(wxContent);
        } else {
            text.setContent(content);
        }

        return textMessageToXml(text);
    }

    /**
     * 图片信息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImage(String toUserName,String fromUserName,String picUrl,String mediaId,String msgid){
        System.out.println("进入图片信息111111------");
        ImageMessage image = new ImageMessage();
        image.setFromUserName(toUserName);
        image.setToUserName(fromUserName);
        image.setMsgType(MessageUtil.MESSAGE_IMAGE);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        image.setCreateTime(dateNowStr);
        image.setPicUrl(picUrl);
        image.setMediaId(mediaId);
        image.setMsgId(msgid);
        System.out.println("进入图片信息111111---picUrl---"+picUrl);
        System.out.println("进入图片信息111111---mediaId---"+mediaId);
        System.out.println("进入图片信息111111---msgid---"+msgid);
        return imageMessageToXml(image);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎进入斐龑时间...;\n\n");
        sb.append("1.斐\n");
        sb.append("2.龑\n\n");
        sb.append("回复 ？ 调出菜单。\n\n");
        return sb.toString();
    }

    /**
     * 1菜单
     * @return
     */
    public static String firstMenuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("111111/...;\n\n");
        return sb.toString();
    }

    /**
     * 2菜单
     * @return
     */
    public static String secondMenuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("222222...;\n\n");
        return sb.toString();
    }

    /**
     * other
     * @return
     */
    public static String otherMenuText(String content){
        StringBuffer sb = new StringBuffer();
        sb.append(content);
        return sb.toString();
    }

}

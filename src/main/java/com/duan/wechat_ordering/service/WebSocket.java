package com.duan.wechat_ordering.service;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {


    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketset=new CopyOnWriteArraySet<>();
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketset.add(this);
        System.out.println("有新的连接！！！ 连接总数为:"+webSocketset.size());
    }
    @OnClose
    public void onClose(){
        webSocketset.remove(this);
        System.out.println("连接断开！！！！ 连接总数为:"+webSocketset.size());
    }
    @OnMessage
    public void onMessage(String message){
        System.out.println("收到客户端发来的消息："+message);
    }
    public void sendMessage(String message){
        for(WebSocket webSocket:webSocketset){
            System.out.println("websocket广播消息："+message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

# ChatRoom

## 项目描述

基于javeweb开发的B/S群聊系统，实现多人聊天功能

## 采用技术及设计

系统设计：采用Servlet+JSP+JavaBean+JDBC

一共5个jsp页面，main.jsp   index.jsp  online.jsp  regist.jsp  safe.jsp

上线下线：使用HttpSessionBindingEvent监听技术，在User类中设置HTTPSession监听，当登录的时候将这个用户的HTTPSession加入到map中，退出的时候从map中去掉。

群聊：遍历map，给在线的用户发送的消息（这里有个bug，就是比我登录迟的是看不到我在线的，只有发送所有人的消息时才会收到）

艾特消息：艾特在线列表中的一个好友发送消息给他（所有人能看见）

## 实现功能

实现用户的注册，登录，一对多、多对一的聊天功能，管理员踢下线（暂时未实现还有bug）。

## 页面展示

![页面](./01.png)
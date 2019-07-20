# ChatRoom

## 项目描述

基于javeweb开发的B/S群聊系统，实现多人聊天功能

## 采用技术

系统设计：采用Servlet+JSP+JavaBean+JDBC

页面：main.jsp  online.jsp  safe.jsp  regist.jsp

技术特色：采用HttpSessionBindingEvent监听好友上线，当好友上线后，为当前用户创建HTTPSession并加入该用户的Map中，当好友下线时，从map中移除当前好友的session。

群聊：遍历当前Map在线好友发送消息（此中有个bug暂未修改）。

单聊：艾特你所要对话的好友，发送消息。

## 实现功能

实现用户的注册，登录，一对多、多对一的聊天功能，管理员踢下线。

## 页面展示

![页面](./01.png)

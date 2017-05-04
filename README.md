# Job Hunter (工作寻)

----

### Author:Han

### E-mail:294098789@qq.com

----

## 项目简介

* 该项目为本科毕业设计项目，主要解决招聘信息过多，无法汇总的问题。

* 功能一：在线爬取招聘信息，输入职位关键词，选择城市，工作经验等，实时获取招聘信息。

* 功能二：添加爬取任务，实现离线分析。

## 使用框架

* 传统Java项目，使用Spring Boot +　MyBatis搭建

* 爬虫框架为Jsoup

* 数据分析表格使用ECharts

## 启动项目

* 复制src/main/resources/application-dev.properties为application.properties，并填写MySQL和Redis的信息。

* 复制src/main/resources/logback-dev.xml为logback.xml，参数LOG_HOME为绝对路径，如/home/xxx/logs

## 注意事项

* 在爬取拉勾职位的时候，Header必须和正常访问一一对应，如果不同，则会被标记为爬虫，爬取速度不能过快，否则也会被标记为爬虫！
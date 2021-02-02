# BrowserPic-java
无头浏览器自动登录网站，点击页面按钮，截图

## pom.xml
```
	<dependency>
		<groupId>org.seleniumhq.selenium</groupId>
		<artifactId>selenium-java</artifactId>
		<version>3.141.59</version>
	</dependency>
```

-- 驱动的版本号和浏览器的版本号一致
-- 在客户端执行，如果要在服务端的话，需要安装浏览器

## 在http上正常，https就不行，加上chromeOptions.addArguments("--ignore-certificate-errors");








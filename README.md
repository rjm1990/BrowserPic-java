# BrowserPic-java
无头浏览器自动登录网站，点击页面按钮，截图

##pom.xml
```
    <dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>3.141.59</version>
		</dependency>
```

## 在http上正常，https就不行，加上chromeOptions.addArguments("--ignore-certificate-errors");

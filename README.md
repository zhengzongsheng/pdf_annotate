# pdf_annotate(在线pdf编辑器)

一个在线编辑pdf文档的页面，通过HTML 5 中Canvas API，获取批注效果的base64编码，传入后台进行解码，与之对应的pdf进行合并，从而达到一个完整的批注效果

## 依赖
pdfbox 2.0.9
<br>
itextpdf 5.5.13

## 目录说明
-src/
<br>
----main/
<br>
--------java/
<br>
------------Dao/
<br>
----------------CRUD.java
<br>
----------------MyBatisUtil.java
<br>
----------------PdfDataMapper.xml
<br>
------------Domain/
<br>
----------------PdfData.java
<br>
------------Filter/
<br>
----------------EncodingFilter.java
<br>
----------------ValidateFilter.java
<br>
------------Service/
<br>
----------------ImgToImg.java
<br>
----------------ImgToPdf.java
<br>
----------------PdfToImg.java
<br>
----------------UploadTreatment.java
<br>
------------Servlet/
<br>
----------------CreateFolder.java
<br>
----------------DownloadDocument.java
<br>
----------------Encode.java
<br>
----------------GetNumber.java
<br>
----------------Magic.java
<br>
----------------SaveCorrect.java
<br>
----------------SelectData.java
<br>
----------------UploadServlet.java
<br>
-.settings/
<br>
-target/
<br>
-.classpath
<br>
-.project
<br>
-pom.xml
<br>
-README.md
<br>
-website.sql

## 数据库部署说明

1.新建数据库website
<br>
2.导入website.sql文件

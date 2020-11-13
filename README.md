# ssm_Oracle
maven+ssm+oracle 练习

大体上照着 https://github.com/Krryxa/maven_SSM.git 写的，dao层做了点修改

搭建ssm框架过程中遇到的问题：
1、刚新建完maven项目后，只有src/main/resource目录，这是因为默认的jre版本太低导致，右键项目——>build path更改jre版本即可

2、代码写完了，但是无法添加到tomcat服务器，这是因为这时还不是web项目，右键项目——>build path 选择project facets,点击convert ，然后勾选dynamic web module,若java版本过低，则修改java版本

3、添加到服务器后，发现部署后，项目在服务器上的文件夹是空的，右键项目——>build path 选择 web deployment assembly，点击add ，选择folder ，选择src/main/webapp，点击确定。再次点击add，选择Java build path entries，点击确定

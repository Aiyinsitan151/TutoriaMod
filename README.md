

## 1.JDK17的下载

下载连接：[Latest Releases | Adoptium](https://adoptium.net/temurin/releases/?version=17&mode=filter&os=any&arch=any)

选择JDK17-LTS，根据自己的版本进行下载

![image-20260721141540523](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721141540523.png)

下载完成后进行安装，注意：在安装过程中可以选择安装路径，建议给Java放在一个专门的路径一边查找，比如我就放在D:\Java\jdk-17\里面

![image-20260721141944971](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721141944971.png)

## 2.IDEA的安装

下载连接：[下载 IntelliJ IDEA](https://www.jetbrains.com.cn/idea/download/?section=windows)

IDEA在2025.3 后统一单安装包，不再区分专业版和社区版，可免费试用专业功能30天

![image-20260721143024937](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721143024937.png)

同样建议大家自定义安装路径，同时为Java项目专门创建一个项目文件夹用来放项目文件，下面第一个是idea的安装目录，第二个是放项目的目录）

![image-20260721144340676](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721144340676.png)

## 3.MDK的获取

MDK的获取方式有两种，forge官网下载和IDEA插件下载（推荐）

###官网获取

下载连接：[Downloads for Minecraft Forge for Minecraft 1.20.1](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)（需要科学上网）

![image-20260721143556764](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721143556764.png)

我们选择最新版本，就是左边那个，点击MDK

![image-20260721143745741](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721143745741.png)

这里我们等待5秒直到右上角出现SKIP按钮，点击SKIP。这样就会下载一个压缩包，我们在项目文件夹中创建一个新的文件，然后把压缩包解压，把文件放在我们新建的文件夹中

![image-20260721144515970](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721144515970.png)

最后用Idea打开。打开Idea，点击打开，选择MDK项目文件，点击选择此文件

![image-20260721145059699](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721145059699.png)

![image-20260721144811495](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721144811495.png)

这样我们就打开了项目文件。

### IDEA插件直接获取

我们在设置里找到插件选项，点击Marketplace,搜索Minecraft Development，点击安装，重启IDEA。重启后我们新建项目，点击左上角文件->新建->项目，我们可以看到右边有个Minecraft,点这个。这里我们注意一下主类，这里我写了org.nothink.tutorial.Tutorial，实际上,这是Java项目结构的一个习惯org.作者名字.模组ID.主类名。JDK选择我们刚下载的JDK17，迪纳基创建

![](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721150325162.png)

无论用哪种方式创建项目，当完成项目的创建都会进行Gradle的自动构建（需要魔法），我们可以通过查看构建控制台来查看构建是否完成，当出现BUILD SUCCESSFUL时，代表构建成功

![image-20260721151944770](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721151944770.png)

## 4.项目的初始化

项目的创建和Gradle的构建完成后，我们不着急进行模组的开发，我们首先要对项目进行一些修改。下面是用插件获取的MDK的项目结构。其中，Tutoriamod就是主类

![image-20260721154600299](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721154600299.png)

### 1.确定JDK的版本

我们首先打开项目结构检查项目所使用的JDK版本

![image-20260721152414502](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721152414502.png)

![image-20260721152427187](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721152427187.png)

然后在设置里检查Gradle的构建版本

![image-20260721152505500](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721152505500.png)

### 2.更改映射

原版官方映射所有函数参数都是 `p_xxxx_`，写代码时完全不知道每个参数代表什么，换成 Parchment 后参数自带语义名称，写逻辑、覆写原版方法速度翻倍。

首先我们在右侧找到gradle.properties文件

找到图示部分，看到第28行有个连接，这个是Parchment官网，ctrl+鼠标左键可快速进入连接。

![image-20260721181031549](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721181031549.png)

![image-20260721153806858](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721153806858.png)

我们找到1.20.1，记住右边的日期，回到IDEA 。将mapping_channel的值改为parchment，mapping_version的值改成2003.09.03-1.20.1。实际上上面注释的表格就说了输入格式。

然后我们回到Parchment官网，往下滑，找到这两个

![image-20260721154231630](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721154231630.png)

把第一个maven部分复制下来粘贴到settings.gralde中

![image-20260721154655044](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721154655044.png)

把第二个id行也复制下来粘贴到biuld.gradle中

![image-20260721154636463](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721154636463.png)

完成后点击右上角的大象，或者点击右侧的大象点击左上角刷新，重新构建Gradle

![image-20260721154932592](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721154932592.png)



这里的配置通常不影响开发过程，但可以改善开发体验

 ###3. 屏蔽@Removal标记

点进主类可以看到，有些地方idea给出了红色标记，如果你直接运行游戏，在编译时也会提醒你“API即将被弃用”。不要害怕，这只是一种警告，如果你实在看不顺眼，可以屏蔽掉它

在build.gradle文件最后，加上209行的内容

options.compilerArgs += ["-Xlint:-removal"]

![image-20260721182321439](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721182321439.png)

然后找到有红色标记的地方，鼠标选中，按住alt+enter,点图中第一个，然后点禁用检查

![image-20260721193321722](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721193321722.png)

###4. 关闭Forge证书校验

在网络代理环境下，下载网络资源容易触发Forge证书校验失败。这时我们只需要修改gradle.properties 文件的第一行，也就是再后面添加

-Dnet.minecraftforge.gradle.check.certs=false

![image-20260721183009240](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721183009240.png)

### 5.主类的改造

点进主类，我们发现已经有了许多代码，但很大一部分代码都只是示例。实际上我们不会在主类写很多代码，主类仅做注册用。所以我们要改造主类，删除这些示例，让主类更加简洁清晰。

这里不说为什么可以删，只展示删完后的结构

![image-20260721195310041](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721195310041.png)

这样是不是清晰了很多

同时在主类的开头，我们可以看到这一行代码

![image-20260721195340036](C:\Users\a\AppData\Roaming\Typora\typora-user-images\image-20260721195340036.png)

这里建议把MODID改成MOD_ID，没有别的原因，只是大多数人看得比较顺眼。

由此，我们就完成了开发环境基本构建，以后我们所有的代码开发都将在这套开发环境上进行。




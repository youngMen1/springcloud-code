## 日志
logger.isErrorEnabled()
2.简单来说，就是用isDebugEnabled方法判断下是能提升性能的。（From: http://blog.sina.com.cn/s/blog_616b57310100f36s.html ）
 if (logger.isInfoEnabled()) {         logger.info("User " + userId + " is using app " + appId);     }
为什么要加上logger.isInfoEnabled()？原因有两点。
1.直接使用logger.info("User " + userId + " is using app " + appId)来输出log，也能够达到log级别为INFO或在INFO以下时才输出：("User " + userId + " is using app " + appId)，因为logger.info方法内部有判断输出级别的代码。但是在进入logger.info函数之前，("User " + userId + " is using app " + appId) 这个表达式已经通过运算拼接成了一个字符串；而如果事先使用 if (logger.isInfoEnabled())进行判断，那么当log级别在INFO以上时，就能省去上述的字符串操作，在高并发和复杂log信息拼接的情况下，使用这种标准的方法输出log能够省去不小的系统开销。另外，如果构造log信息的过程需要大量字符串操作，建议使用StringBuilder来完成字符串拼接。
2.ERROR及其以上级别的log信息是一定会被输出的，所以只有logger.isDebugEnabled和logger.isInfoEnabled方法，而没有logger.isErrorEnabled方法。
## Google guava工具类的介绍和使用
guava的优点：
高效设计良好的API，被Google的开发者设计，实现和使用
遵循高效的java语法实践
使代码更刻度，简洁，简单
节约时间，资源，提高生产力

Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库，例如：
集合 [collections]
缓存 [caching]
原生类型支持 [primitives support]
并发库 [concurrency libraries]
通用注解 [common annotations]
字符串处理 [string processing]
I/O 等等。

## 参考
集合 [collections]:
https://www.oschina.net/translate/beautiful-code-with-google-collections-guava-and-static-imports-part-1
https://www.jianshu.com/p/7bf5dc61ca06

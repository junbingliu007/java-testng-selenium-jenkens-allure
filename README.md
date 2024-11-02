# Java + Selenium + TestNG + Allure



项目目录结构：

src/mian/java/org/ceiling

- api : 存放项目访问路径位置
- base : 存放访问元素以及操作元素的方法，用于后续继承
  - BasePage : 访问元素
  - BaseHandler : 操作元素
- controller: 执行页面流程逻辑处理
- enums: 自定义的枚举类
  - BrowserType : 表示浏览器的支持类型
- pages: 表示页面元素的存储
- service: 表示操作页面元素
- utils: 表示常用工具类
- test: 存放测试用例的位置
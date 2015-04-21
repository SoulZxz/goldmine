A simple web traffic data collecting app. To use it, serveral things must be config first.

1. Config server host in spring-conf/spring.xml. For instance serverHost = http://www.myapp.com.

2. Include ${serverHost}/webstat/api/statScript?appId=${myApp}&logPv=true javascript on your page.

3. For user action tracking, use webstat.logCustomEvent().

See html/ref.html and html/1.html for details.
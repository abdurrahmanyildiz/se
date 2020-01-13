#This project is a search engine crawler project.

#Created application can search on 4 search engines for your keywords. You can manage application by editing properties file.

#The app.properties and logging.properties files and JavaSE-1.8 are required for the project to run.

#In order for the project to read and work keywords, keywords.txt files are needed. This file should be under the resource folder.

#When the project is turned into a jar, there must be a folder named resources in the directory where the jar is located.

#The app.properties and logging.properties files must be in the Resources folder. In app.properties file you can disable or enable spesific search engine, live logging on console, max result per search on engines or you can set sleep time to not act like robots. And via logging.properties file you can manage logging options.   

#The Resources folder must also contain the chromedriver_win32 folder.

#The chromedriver_win32 folder should also contain the necessary drivers for chromedriver.exe / firefox, gecko or linux.

#If Firefox Gecko driver is to be used, webdriver.browser = firefox must be active in the app.properties file.

#Similarly, the chromedriver_win32 folder should contain Firefox webdriver.

#If chrome / firefox binary error is received on the machine where the application will run, chrome / firefox application must be installed on the machine.

#In case of error in creating the log file in case of running the application, logs folder is created at the same level as the jar file.

#Within this folder 2 files with the names log0.log and log0.log.lck must be created.


#********************** IN TURKISH *****************

#Bu proje arama motoru veri toplama projesidir.

#Projenin çalışabilmesi için JavaSE-1.8, app.properties ve logging.properties dosyalarına ihtiyaç vardır.

#Projenin keywordleri okuyup çalışabilmesi için yildiz_category_map.txt ve keywords.txt dosyalarına ihtiyaç vardır. Bu dosyalar resource klasörü altında olmalıdır.

#Proje jar haline getirildiği zaman jar bulunduğu dizinde resources isminde bir klasör olmalıdır.

#Resources klasörünün içinde app.properties ve logging.properties dosyaları bulunmalıdır.

#Resources klasörünün içinde ayrıca chromedriver_win32 klasörü bulunmalıdır.

#chromedriver_win32 klasörü içinde de chromedriver.exe/firefox, gecko veya linux için gerekli driverlar bulunmalıdır.

#Eğer Firefox Gecko driver kullanılacaksa app.properties dosyası içinden webdriver.browser=firefox aktif olmalıdır.
#Aynı şekilde chromedriver_win32 klasörü içinde Firefox webdriver bulunmalıdır.

#Uygulamanın çalışacağı makinede chrome/firefox binary hatası alınırsa makineye chrome/firefox uygulaması yüklenmelidir.

#Uygulamanın çalıştırılması durumunda log dosyasının oluşturulmasında hata alınması durumunda jar dosyasıyla aynı seviyede logs klasörü oluşturulur.
#Bu klasörün içine log0.log ve log0.log.lck isimleriyle 2 dosya oluşturulmalıdır.

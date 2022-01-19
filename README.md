# Yemeksepeti Otomasyon Çalışması

Test senaryoları ilgili feature dosyalarının altında bulunmaktadır. Farklı sayfalarda nesnelerin tutulması sağlandı. Otomasyon projesi için Maven kullanıldı. Raporlama için TestNG Reporter kullanıldı. Testlerin çalışması için gerekli olan Dependency'ler pom.xml dosyasında yer almaktadır. Chrome ve Firefox WebDriver'ları proje altında /Drivers klasörünte tutulmaktadır. Çalışan son testlerin raporu /test-output dosyasında emailable-report.html içerisinde yer almakta olup, sistemde yüklü olan bir browser ile açılarak görüntülenebilir. Fail olan case'in fail olma anına ait ekran görüntüsü proje altında case adı ve sistem tarih-saati içeren bir isimle /ScreenShots dosyasında konumlanmaktadır.

Bulunan Bug:

- Hatalı kullanıcı adı ve parola girişinden sonra verilen hata mesajı popup'ında bulunmayan bir obje aranarak case fail durumuna düşürülmüştür.
   
Kullanılan Java versiyonu:

- JDK1.8
   
Projede Kullanılan Framework'ler:

- Selenium 3.141.59
- TestNG Reporter 6.8
- Maven 3.0.0

# Testlerin Çalıştırılması

Testler paralel ve corssbrowser olarak feature dosyalarında bulunan senaryoları koşmaktadır. Chrome ve Firefox driver'ları proje dosyası altında Drivers klasöründe tutulmaktadır.



Projenin yapılışı ile ilgili Not: Bu projeyi daha önce Selenium, IntelliJ, TestNG, Java kullanmış olmadan/bilmeden yapmış bulunmaktayım, bu ilk tecrübem oldu. Raporlama için Extent Report kullanmayı denedim, yapıyı oluşturdum fakat sadece rapor çıktısı html'i oluşturamadım. Proje için yoğun mesaili bir dönemde efor harcadım, ortalama 1 haftada 50 saat harcayabilerek göndermiş bulunuyorum. Yaptığım bu otomasyon çalışmasından %30 oranında memnunum diyebilirim. Öğrenerek, örnekler/videolar üzerinden çalıştığım için mimariyi tam anlamıyla kuramadım, bu durum da raporlama için gereken yapıyı iyi bir şekilde oluşturamamama neden oldu. 
# leagues
## Opis
System przeznaczony jest dla średniej wielkości przedsiębiorstwa zajmującego się przeprowadzaniem rozgrywek 
ligowych w sportach takich jak: tenis, badminton, squach oraz sporty walki. Przez pojęcie liga rozumiemy, 
zebraną grupę osób które ze sobą grają (Dla przykładu mecz squacha trwa krótko, kilka minut koniec, 
zbieramy 8 osób gramy 7 meczy, wynajmujemy dwa korty i sprawę mamy załatwioną. A tenisa może odbywać się kilka godzin,
więc liga będzie trwała zazwyczaj tydzień/miesiąc). Każda z nich ma określone miejsce i czas w których ma się odbywać,
rozgrywki są cykliczne oraz posiadają własne zasady punktowania i klasyfikacji, a system oferować ma kontrolę nad
wynajmowanym obiektem (monitorowanie oraz ustalanie kto gdzie gra, koszty, które są różne w zależności od miejsca),
posiadać ranking, historię rozgrywek oraz kontrolę nad awansem i spadkiem z poszczególnych lig zawodników po
rozgranym sezonie. Opłaty mogą być uiszczane bezpośrednio (przed każdym meczem, wyższa kwota) lub po n spotkań z
góry z rabatem. Nikt nie może wypaść z ligi. Ilość lig zależna będzie od liczby graczy. Informacje 
(czas spotkań, wyniki, rankingi) mają być dostępne dla uczestników rozgrywek. 
Mecze mają posiadać również małe punkty, żebyśmy jednogłośnie mogli stwierdzić kto wygrał oraz prawidłowo odwzorować ranking.
Pracownik ma mieć dostęp do wirtualnych kont uczestników, pozwalających na kontrolę nad wpłatami 
(także obsługę zniżek i odwołań) oraz wprowadzanie wyników dla poszczególnych spotkań, zarządzanie sprawami organizacyjnymi.
Księgowa firmy ma mieć także dostęp do poszczególnych wpłat uczestników. Opłaty mogą być uiszczane przelewem/gotówka 
i wtedy księgowa ma możliwość dodania, zaksięgowania wpłaty. System ma także posiadać system raportów finansowych
pozwalających na ocenę rentowności poszczególnych lig (która jest najbardziej zyskowna, kiedy interes przestaje się opłacać,
np. Dla 5 osób dla squatcha może to być na granicy, dla 6 będzie już lepiej, dla 8 najlepiej). Miesiąc po miesiącu system
ma dawać raport. Uczestnicy nie posiadają dostępu do swoich danych, informacje dostępne są jawne. 
Tylko właściciel ma możliwość edycji oraz dodawania rozgrywek.

## Technologie
##### Backend
* Java 8 
* Spring Boot
* Spring Security
* MyBatis
##### Frontend
* Bootstrap + Thymeleaf
##### Baza danych 
* MySQL
##### Dependency Management, IDE
* Maven, IntellijJ IDEA
## Preview 
![](https://media.giphy.com/media/H35XA6JSCVvpsbtPDe/giphy.gif)
## Diagram EER, wybrane scenariusze
![eer](/docs/img/eer.png?raw=true "eer") 
![scenariusz](/docs/img/scenar-1.png?raw=true "scenariusz") 
![scenariusz](/docs/img/scenar-2.png?raw=true "scenariusz") 
![scenariusz](/docs/img/scenar-3.png?raw=true "scenariusz") 
![scenariusz](/docs/img/scenar-4.png?raw=true "scenariusz") 
![view](/docs/img/scrn-1.png?raw=true "view") 

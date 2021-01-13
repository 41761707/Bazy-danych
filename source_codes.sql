-- Inicjalizacja bazy danych
DROP DATABASE IF EXISTS dziennik;
CREATE DATABASE dziennik;
-- przejscie do nowo utworzonej bazy
USE dziennik;
-- Utworzenie tabeli Uczen
CREATE TABLE Uczen (
	nrLegitymacji varchar(10) NOT NULL,
	Imie varchar(40) NOT NULL,
	Nazwisko varchar(40) NOT NULL,
	ID_Adresu int unsigned NOT NULL,
	ID_Klasy int unsigned NOT NULL,
	PESEL varchar(11) NOT NULL,
	nrTelefonu varchar(9),
	Email varchar(40) NOT NULL,
	Login varchar(40) NOT NULL,
	Haslo varchar(50) NOT NULL,
	PRIMARY KEY(nrLegitymacji),
	UNIQUE(nrLegitymacji) ,
	UNIQUE(PESEL)
);
-- Utworzenie tabeli Nauczyciel
CREATE TABLE Nauczyciel(
	ID_Nauczyciela int unsigned NOT NULL AUTO_INCREMENT,
	ID_Adresu int unsigned NOT NULL,
	nrGabinetu int unsigned,
	Imie varchar(40) NOT NULL,
	Nazwisko varchar(40) NOT NULL,
	PESEL varchar(11) NOT NULL,
	nrTelefonu varchar(9) NOT NULL,
	Email varchar(40) NOT NULL,
	Login varchar(40) NOT NULL,
	Haslo varchar(50) NOT NULL,
	PRIMARY KEY(ID_Nauczyciela)
);
-- Utworzenie tabeli Administrator
CREATE TABLE Administrator(
	ID_Administratora int unsigned NOT NULL AUTO_INCREMENT,
	Imie varchar(40) NOT NULL,
	Nazwisko varchar(40) NOT NULL,
	PESEL varchar(11) NOT NULL,
	ID_Adresu int unsigned NOT NULL,
	nrTelefonu varchar(9) NOT NULL,
	Email varchar(40) NOT NULL,
	Login varchar(40) NOT NULL,
	Haslo varchar(50) NOT NULL,
	PRIMARY KEY(ID_Administratora)
);
-- Utworzenie tabeli Opiekun
CREATE TABLE Opiekun(
	ID_Opiekuna int unsigned NOT NULL AUTO_INCREMENT,
	Imie varchar(40) NOT NULL,
	Nazwisko varchar(40) NOT NULL,
	ID_Adresu int unsigned NOT NULL,
	PESEL varchar(11) NOT NULL,
	nrTelefonu varchar(9) NOT NULL,
	Email varchar(40) NOT NULL,
	Login varchar(40) NOT NULL,
	Haslo varchar(50) NOT NULL,
	PRIMARY KEY(ID_Opiekuna)
);	
-- Utworzenie tabeli Ocena
CREATE TABLE Ocena(
	ID_Oceny int unsigned NOT NULL AUTO_INCREMENT,
	nrLegitymacjiUcznia VARCHAR(11) NOT NULL,
	ID_Nauczyciela int unsigned NOT NULL,
	ID_Przedmiotu int unsigned NOT NULL,
	Data DATE NOT NULL,
	Ocena DECIMAL(3,2) NOT NULL,
	Komentarz varchar(500),
	PRIMARY KEY(ID_Oceny)
);
-- Utworzenie tabeli Klasa
CREATE TABLE Klasa(
	ID_Klasy int unsigned NOT NULL AUTO_INCREMENT,
	Wychowawca int unsigned NOT NULL,
	Rocznik int unsigned NOT NULL,
	Oddzial varchar(3) NOT NULL,
	profil varchar(40),
	liczebnosc int unsigned NOT NULL,
	PRIMARY KEY(ID_Klasy)
);
-- Utworzenie tabeli Przedmiot
CREATE TABLE Przedmiot(
	ID_Przedmiotu int unsigned NOT NULL AUTO_INCREMENT,
	Nazwa varchar(40) NOT NULL,
	PRIMARY KEY(ID_Przedmiotu)
);
-- Utworzenie tabeli Adres
CREATE TABLE Adres(
	ID_Adresu int unsigned NOT NULL AUTO_INCREMENT,
	Miejscowosc VARCHAR(40) NOT NULL,
	KodPocztowy VARCHAR(6) NOT NULL,
	Ulica VARCHAR(40),
	nrDomu int unsigned NOT NULL,
	nrMieszkania int unsigned,
	PRIMARY KEY(ID_Adresu)
);
-- Utworzenie tabeli Zachowanie
CREATE TABLE Zachowanie(
	nrLegitymacjiUcznia VARCHAR(11) NOT NULL,
	PunktyZachowania int,
	UNIQUE(nrLegitymacjiUcznia)
);
-- Utworzenie tabeli Uwaga
CREATE TABLE Uwaga(
	ID_Uwagi int unsigned NOT NULL AUTO_INCREMENT,
	nrLegitymacjiUcznia varchar(11) NOT NULL,
	ID_Nauczyciela int unsigned NOT NULL,
	OdjetePunkty int NOT NULL,
	Komentarz varchar(500),
	PRIMARY KEY(ID_Uwagi)
);
-- Utworzenie tabeli JednostkaLekcyjna
-- Dodano juz wszystkie tabele, ktore nie sa pomostowe, wiec
-- od razu w definicji tabeli umieszczamy relacje
CREATE TABLE JednostkaLekcyjna(
	ID_Nauczyciela int unsigned NOT NULL,
	ID_Klasy int unsigned NOT NULL,
	ID_Przedmiotu int unsigned NOT NULL,
	FOREIGN KEY (ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela) ON DELETE CASCADE, 
	FOREIGN KEY (ID_Klasy) REFERENCES Klasa(ID_Klasy) ON DELETE CASCADE,
	FOREIGN KEY (ID_Przedmiotu) REFERENCES Przedmiot(ID_Przedmiotu) ON DELETE CASCADE
);
-- Utworzenie OpiekunUcznia
CREATE TABLE OpiekunUcznia(
	nrLegitymacjiUcznia varchar(11) NOT NULL,
	ID_Opiekuna int unsigned NOT NULL,
	FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(NrLegitymacji) ON DELETE CASCADE,
	FOREIGN KEY(ID_Opiekuna) REFERENCES Opiekun(ID_Opiekuna) ON DELETE CASCADE
);
-- dodawanie relacji dla tabeli Uczen
ALTER TABLE Uczen
ADD FOREIGN KEY (ID_Klasy) REFERENCES Klasa(ID_Klasy) ON DELETE CASCADE;
ALTER TABLE Uczen
ADD FOREIGN KEY (ID_Adresu) REFERENCES Adres(ID_Adresu) ON DELETE CASCADE; 
-- dodawanie relacji dla tabeli Nauczyciel
ALTER TABLE Nauczyciel
ADD FOREIGN KEY(ID_Adresu) REFERENCES Adres(ID_Adresu) ON DELETE CASCADE;
-- dodawanie relacji dla tabeli Administrator
ALTER TABLE Administrator
ADD FOREIGN KEY(ID_Adresu) REFERENCES Adres(ID_Adresu) ON DELETE CASCADE;
-- dodawanie relacji dla Opiekuna
ALTER TABLE Opiekun
ADD FOREIGN KEY(ID_ADresu) REFERENCES Adres(ID_Adresu) ON DELETE CASCADE;
-- dodawanie relacji dla Ocena
ALTER TABLE Ocena
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji) ON DELETE CASCADE;
ALTER TABLE Ocena
ADD FOREIGN KEY(ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela) ON DELETE CASCADE;
ALTER TABLE Ocena
ADD FOREIGN KEY(ID_Przedmiotu) REFERENCES Przedmiot(ID_Przedmiotu) ON DELETE CASCADE;
-- dodawanie relacji dla Klasa
ALTER TABLE Klasa
ADD FOREIGN KEY(Wychowawca) REFERENCES Nauczyciel(ID_Nauczyciela) ON DELETE CASCADE;
-- dodawanie relacji dla Zachowania
ALTER TABLE Zachowanie
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji) ON DELETE CASCADE;
-- dodawanie relacji dla Uwaga
ALTER TABLE Uwaga
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji) ON DELETE CASCADE;
ALTER TABLE Uwaga
ADD FOREIGN KEY(ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela) ON DELETE CASCADE;

-- trigger dla wstawiania danych do tabel
-- Uczen,Nauczyciel,Administrator,Opiekun


-- 1trigger dla kontroli liczebnosci klasy
-- dla insert
DROP TRIGGER IF EXISTS ile_osob_w_klasie_INSERT;
DELIMITER $$
CREATE TRIGGER ile_osob_w_klasie_INSERT BEFORE INSERT ON Uczen
FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
SET n=NEW.ID_Klasy;
UPDATE Klasa SET Liczebnosc=(SELECT Liczebnosc+1 FROM Klasa WHERE ID_Klasy=n) WHERE ID_Klasy=n;
END $$
DELIMITER ;
-- dla update
DROP TRIGGER IF EXISTS ile_osob_w_klasie_UPDATE;
DELIMITER $$
CREATE TRIGGER ile_osob_w_klasie_UPDATE BEFORE UPDATE ON Uczen
FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE o INT DEFAULT 0;
SET n=NEW.ID_Klasy;
SET o=OLD.ID_Klasy;
UPDATE Klasa SET Liczebnosc=(SELECT Liczebnosc+1 FROM Klasa WHERE ID_Klasy=n) WHERE ID_Klasy=n;
UPDATE Klasa SET Liczebnosc=(SELECT Liczebnosc-1 FROM Klasa WHERE ID_Klasy=o) WHERE ID_Klasy=o;
END $$
DELIMITER ;
-- dla delete
DROP TRIGGER IF EXISTS ile_osob_w_klasie_DELETE;
DELIMITER $$
CREATE TRIGGER ile_osob_w_klasie_DELETE BEFORE DELETE ON Uczen
FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
SET n=OLD.ID_Klasy;
UPDATE Klasa SET Liczebnosc=(SELECT Liczebnosc-1 FROM Klasa WHERE ID_Klasy=n) WHERE ID_Klasy=n;
END $$
DELIMITER ;

-- podobny lista triggerow dla zachowania
-- dla insert
DROP TRIGGER IF EXISTS zachowanie_INSERT;
DELIMITER $$
CREATE TRIGGER zachowanie_INSERT BEFORE INSERT ON Uwaga
FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE uczen VARCHAR(11);
SET n=NEW.OdjetePunkty;
SET uczen=NEW.nrLegitymacjiUcznia;
UPDATE Zachowanie SET PunktyZachowania=(SELECT PunktyZachowania+n FROM Zachowanie WHERE nrLegitymacjiUcznia=uczen) WHERE nrLegitymacjiUcznia=uczen;
END $$
DELIMITER ;
-- 2procedura dodajaca ocene do tabeli Oceny
DROP PROCEDURE IF EXISTS dodaj_ocene;
DELIMITER $$
CREATE PROCEDURE dodaj_ocene(IN nrLegitymacjiUcznia VARCHAR(11),IN ID_Nauczyciela INT unsigned,IN ID_Przedmiotu INT unsigned,IN Data DATE, IN Ocena DECIMAL(3,2),IN Komentarz VARCHAR(500))
BEGIN
	DECLARE q VARCHAR(300);
	IF ((SELECT nrLegitymacjiUcznia) NOT IN (SELECT nrLegitymacji FROM Uczen)) THEN
        SELECT 'Brak ucznia o podanym numerze legitymacji';
    ELSE
	SET q='INSERT INTO Ocena(nrLegitymacjiUcznia,ID_Nauczyciela,ID_Przedmiotu,Data,Ocena,Komentarz) VALUES(?,?,?,?,?,?);';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING nrLegitymacjiUcznia,ID_Nauczyciela,ID_Przedmiotu,Data,Ocena,Komentarz;
	DEALLOCATE PREPARE stmnt;
	END IF;
END $$
DELIMITER ;

-- 3procedura dodajaca uwage do tabeli Uwaga
DROP PROCEDURE IF EXISTS dodaj_uwage;
DELIMITER $$
CREATE PROCEDURE dodaj_uwage(IN nrLegitymacjiUcznia VARCHAR(11),IN ID_Nauczyciela INT,IN OdjetePunkty INT,IN Komentarz VARCHAR(500))
BEGIN
	DECLARE q VARCHAR(300);
	IF ((SELECT nrLegitymacjiUcznia) NOT IN (SELECT nrLegitymacji FROM Uczen)) THEN
        SELECT 'Brak ucznia o podanym numerze legitymacji';
    ELSE
	SET q='INSERT INTO Uwaga(nrLegitymacjiUcznia,ID_Nauczyciela,OdjetePunkty,Komentarz) VALUES(? , ? , ? , ? );';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING nrLegitymacjiUcznia, ID_Nauczyciela,OdjetePunkty, Komentarz;
	DEALLOCATE PREPARE stmnt;
	END IF;
END $$
DELIMITER ;

-- 4procedura, ktora dodaje ucznia do tabeli Uczen
DROP PROCEDURE IF EXISTS dodaj_ucznia;
DELIMITER $$
CREATE PROCEDURE dodaj_ucznia(IN nrLegitymacji VARCHAR(11), IN Imie VARCHAR(40), IN Nazwisko VARCHAR(40), IN ID_Adresu INT unsigned, IN ID_Klasy INT unsigned, IN PESEL VARCHAR(11), IN nrTelefonu VARCHAR(9), IN Email VARCHAR(40),IN Login VARCHAR(40), IN Haslo VARCHAR(50))
BEGIN
	DECLARE q VARCHAR(300);
	DECLARE Hash VARCHAR(160);
	SET Hash=SHA1(Haslo);
	IF ((SELECT ID_Adresu) NOT IN (SELECT ID_Adresu FROM Adres)) THEN
        SELECT 'Brak podanego ID adresu w bazie danych';
    ELSEIF ((SELECT ID_Klasy) NOT IN (SELECT ID_Klasy FROM Klasa)) THEN
        SELECT 'Brak podanego ID klasy w bazie danych';
    ELSE
    	SET q='INSERT INTO Uczen(nrLegitymacji,Imie,Nazwisko,ID_Adresu,ID_Klasy,PESEL,nrTelefonu,Email,Login,Haslo) VALUES (?,?,?,?,?,?,?,?,?,?);';
    	PREPARE stmnt FROM q;
    	EXECUTE stmnt USING nrLegitymacji,Imie,Nazwisko,ID_Adresu,ID_Klasy,PESEL,nrTelefonu,Email,Login,Hash;
    	DEALLOCATE PREPARE stmnt;
    END IF;
END $$
DELIMITER ;

-- 5procedura dodajaca nauczyciela do tabeli Nauczyciel
DROP PROCEDURE IF EXISTS dodaj_nauczyciela;
DELIMITER $$
CREATE PROCEDURE dodaj_nauczyciela(IN ID_Adresu INT unsigned,IN nrGabinetu INT unsigned,IN Imie VARCHAR(40),IN Nazwisko VARCHAR(40), IN PESEL VARCHAR(11),IN nrTelefonu VARCHAR(9),IN Email VARCHAR(40),IN Login VARCHAR(40),IN Haslo VARCHAR(40))
BEGIN
	DECLARE q VARCHAR(300);
	DECLARE Hash VARCHAR(160);
	SET Hash=SHA(Haslo);
	IF ((SELECT ID_Adresu) NOT IN (SELECT ID_Adresu FROM Adres)) THEN
        SELECT 'Brak podanego ID adresu w bazie danych';
    ELSE
    	SET q='INSERT INTO Nauczyciel(ID_Adresu,nrGabinetu,Imie,Nazwisko,PESEL,nrTelefonu,Email,Login,Haslo) VALUES (?,?,?,?,?,?,?,?,?);';
    	PREPARE stmnt FROM q;
    	EXECUTE stmnt USING ID_Adresu,nrGabinetu,Imie,Nazwisko,PESEL,nrTelefonu,Email,Login,Hash;
    	DEALLOCATE PREPARE stmnt; 
    END IF;
END $$
DELIMITER ; 

-- 6procedura dodajaca opiekuna do tabeli Opiekunowie
DROP PROCEDURE IF EXISTS dodaj_opiekuna;
DELIMITER $$
CREATE PROCEDURE dodaj_opiekuna(IN Imie VARCHAR(40),IN Nazwisko VARCHAR(40),IN ID_Adresu INT unsigned, IN PESEL VARCHAR(11),IN nrTelefonu VARCHAR(9),IN Email VARCHAR(40),IN Login VARCHAR(40),IN Haslo VARCHAR(40))
BEGIN
	DECLARE q VARCHAR(300);
	DECLARE Hash VARCHAR(160);
	SET Hash=SHA(Haslo);
	IF ((SELECT ID_Adresu) NOT IN (SELECT ID_Adresu FROM Adres)) THEN
        SELECT 'Brak podanego ID adresu w bazie danych';
    ELSE
     SET q='INSERT INTO Opiekun(Imie,Nazwisko,ID_Adresu,PESEL,nrTelefonu,Email,Login,Haslo) VALUES (?,?,?,?,?,?,?,?);';
     PREPARE stmnt FROM q;
     EXECUTE stmnt USING Imie,Nazwisko,ID_Adresu,PESEL,nrTelefonu,Email,Login,Hash;
     DEALLOCATE PREPARE stmnt;
    END IF;
END $$
DELIMITER ;

-- 7procedura, która dla zadanego ucznia zwraca jego średnią z podanego przedmiotu
DROP PROCEDURE IF EXISTS oblicz_srednia;
DELIMITER $$
CREATE PROCEDURE oblicz_srednia(IN nrLegitymacji INT, IN ID_Przedmiotu INT)
BEGIN
   DECLARE q VARCHAR(200);
   SET q = 'SELECT AVG(O.Ocena) AS srednia FROM Ocena O JOIN Uczen U ON O.nrLegitymacjiUcznia = U.nrLegitymacji WHERE U.nrLegitymacji = ?
    AND O.ID_Przedmiotu = ?';
   PREPARE stmnt FROM q;
   EXECUTE stmnt USING nrLegitymacji, ID_Przedmiotu;
   DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;


-- 8 oblicz_średnia_ogólna- funkcja, która dla zadanego ucznia zwraca jego średnią ze wszystkich przedmiotów
DROP PROCEDURE IF EXISTS oblicz_srednia_ogolna;
DELIMITER $$
CREATE PROCEDURE oblicz_srednia_ogolna(IN nrLegitymacji INT)
BEGIN
   DECLARE q VARCHAR(300);
   SET q = 'SELECT AVG(srednia) AS sredniaOgolna FROM
    (SELECT AVG(O.Ocena) AS srednia FROM Ocena O JOIN Uczen U
    ON O.nrLegitymacjiUcznia = U.nrLegitymacji
    WHERE U.nrLegitymacji = ?
    GROUP BY O.ID_Przedmiotu) tab1';
   PREPARE stmnt FROM q;
   EXECUTE stmnt USING nrLegitymacji;
   DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;

-- 9 oblicz_średnia_klasy- funkcja, która dla zadanej klasy zwróci średnią jej uczniów
DROP PROCEDURE IF EXISTS oblicz_srednia_klasy;
DELIMITER $$
CREATE PROCEDURE oblicz_srednia_klasy(IN ID_Klasy INT)
BEGIN
    DECLARE q VARCHAR(300);
    SET q = 'SELECT AVG(srednia) AS sredniaKlasy FROM
        (SELECT U.ID_Klasy AS klasa, AVG(O.Ocena) AS srednia FROM Ocena O JOIN Uczen U
        ON O.nrLegitymacjiUcznia = U.nrLegitymacji
        GROUP BY U.nrLegitymacji, O.ID_Przedmiotu) tab1
    WHERE klasa = ?
    GROUP BY klasa;';
    PREPARE stmnt FROM q;
    EXECUTE stmnt USING ID_Klasy;
    DEALLOCATE PREPARE stmnt;
END $$
DELIMITER;

-- 10procedura dodajaca adres do tabeli Adres
DROP PROCEDURE IF EXISTS dodaj_adres;
DELIMITER $$
CREATE PROCEDURE dodaj_adres(IN Miejscowosc VARCHAR(40), IN KodPocztowy VARCHAR(6), IN Ulica VARCHAR(40), IN nrDomu INT UNSIGNED, IN nrMieszkania INT UNSIGNED)
BEGIN
    DECLARE q VARCHAR(200);
    SET q = 'INSERT INTO Adres(miejscowosc, kodpocztowy, ulica, nrdomu, nrmieszkania) VALUES( ?, ?, ?, ?, ?);';
    PREPARE stmnt FROM q;
    EXECUTE stmnt USING Miejscowosc, KodPocztowy, Ulica, nrDomu, nrMieszkania;
    DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;

-- 11procedura dodajaca klase do tabeli Klasa
DROP PROCEDURE IF EXISTS dodaj_klase;
DELIMITER $$
CREATE PROCEDURE dodaj_klase(IN Wychowawca INT, IN Rocznik INT, IN Oddzial VARCHAR(3), IN profil VARCHAR(40), IN liczebnosc INT)
BEGIN
    DECLARE q VARCHAR(200);
    SET q = 'INSERT INTO Klasa( wychowawca, rocznik, oddzial, profil, liczebnosc) VALUES( ?, ?, ?, ?, ?);';
    PREPARE stmnt FROM q;
    EXECUTE stmnt USING Wychowawca, Rocznik, Oddzial, profil, liczebnosc;
    DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;

-- 12procedura wyświetlająca adres dla podanego użytkownika
DROP PROCEDURE IF EXISTS wyswietl_adres;
DELIMITER $$
CREATE PROCEDURE wyswietl_adres(IN imie VARCHAR(40), IN nazwisko VARCHAR(40))
BEGIN
    DECLARE q VARCHAR(200);
    SET q = 'SELECT A.Miejscowosc, A.KodPocztowy, A.ULica, A.nrDomu, A.nrMieszkania FROM Adres A JOIN Uczen U
            ON A.ID_Adresu = U.ID_Adresu WHERE U.Imie = ? AND U.Nazwisko = ?;';
    PREPARE stmnt FROM q;
    EXECUTE stmnt USING imie, nazwisko;
    DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;
-- 13procedura dodająca przedmiot do tabeli Przedmioty
DROP PROCEDURE IF EXISTS dodaj_przedmiot;
DELIMITER $$
CREATE PROCEDURE dodaj_przedmiot(IN nazwa VARCHAR(40))
BEGIN
	DECLARE q VARCHAR(200);
	SET q='INSERT INTO Przedmiot(Nazwa) VALUES (?);';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING Nazwa;
	DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;

-- 14procedura, ktora dodaje administratora do tabeli Administrator, pomocne w skrypcie generującym dane

DROP PROCEDURE IF EXISTS dodaj_administratora;
DELIMITER $$
CREATE PROCEDURE dodaj_administratora(IN Imie VARCHAR(40), IN Nazwisko VARCHAR(40), IN PESEL VARCHAR(11), IN ID_Adresu INT unsigned, IN nrTelefonu VARCHAR(9), IN Email VARCHAR(40),IN Login VARCHAR(40), IN Haslo VARCHAR(50))
BEGIN
	DECLARE q VARCHAR(300);
	DECLARE Hash VARCHAR(160);
	SET Hash=SHA1(Haslo);
	IF ((SELECT ID_Adresu) NOT IN (SELECT ID_Adresu FROM Adres)) THEN
        SELECT 'Brak podanego ID adresu w bazie danych';
    ELSE
    	SET q='INSERT INTO Administrator(Imie,Nazwisko,PESEL,ID_Adresu,nrTelefonu,Email,Login,Haslo) VALUES(?,?,?,?,?,?,?,?);';
    	PREPARE stmnt FROM q;
    	EXECUTE stmnt USING Imie,Nazwisko,PESEL,ID_Adresu,nrTelefonu,Email,Login,Hash;
    	DEALLOCATE PREPARE stmnt;
    END IF;
END $$
DELIMITER ;

-- 15procedura, ktora dodaje zachowanie, pomocne w skrypcie

DROP PROCEDURE IF EXISTS dodaj_zachowanie;
DELIMITER $$
CREATE PROCEDURE dodaj_zachowanie(IN nrLegitymacjiUcznia VARCHAR(11),IN Punkty INT)
BEGIN
	DECLARE q VARCHAR(200);
	SET q='INSERT INTO Zachowanie(nrLegitymacjiUcznia,PunktyZachowania ) VALUES(?,?);';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING nrLegitymacjiUcznia,Punkty;
	DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;


-- 16procedura,	ktora dodaje jednostke do JednostkiLekcyjnej

DROP PROCEDURE IF EXISTS dodaj_lekcje;
DELIMITER $$
CREATE PROCEDURE dodaj_lekcje(IN ID_Nauczyciela INT unsigned, IN ID_Klasy INT unsigned, IN ID_Przedmiotu INT unsigned)
BEGIN
	DECLARE q VARCHAR(200);
	SET q='INSERT INTO JednostkaLekcyjna(ID_Nauczyciela,ID_Klasy,ID_Przedmiotu) VALUES (?,?,?);';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING ID_Nauczyciela, ID_Klasy, ID_Przedmiotu;
	DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;

-- 17procedura, ktora dodaje relacje opiekun-uczen

DROP PROCEDURE IF EXISTS dodaj_relacje;
DELIMITER $$
CREATE PROCEDURE dodaj_relacje(IN nrLegitymacjiUcznia VARCHAR(11),IN ID_Opiekuna INT unsigned)
BEGIN
	DECLARE q VARCHAR(200);
	SET q='INSERT INTO OpiekunUcznia(nrLegitymacjiUcznia,ID_Opiekuna) VALUES(?,?);';
	PREPARE stmnt FROM q;
	EXECUTE stmnt USING nrLegitymacjiUcznia,ID_Opiekuna;
	DEALLOCATE PREPARE stmnt;
END $$
DELIMITER ;








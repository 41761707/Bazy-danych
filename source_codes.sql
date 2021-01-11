--Utworzenie tabeli Uczen
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
	UNIQUE(nrLegitymacji) 
);
--Utworzenie tabeli Nauczyciel
CREATE TABLE Nauczyciel(
	ID_Nauczyciela int unsigned NOT NULL AUTO_INCREMENT,
	ID_Adresu int unsigned NOT NULL,
	NrGainetu int unsigned,
	Imie varchar(40) NOT NULL,
	Nazwisko varchar(40) NOT NULL,
	PESEL varchar(11) NOT NULL,
	nrTelefonu varchar(9) NOT NULL,
	Email varchar(40) NOT NULL,
	Login varchar(40) NOT NULL,
	Haslo varchar(50) NOT NULL,
	PRIMARY KEY(ID_Nauczyciela)
);
--Utworzenie tabeli Administrator
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
--Utworzenie tabeli Opiekun
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
--Utworzenie tabeli Ocena
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
--Utworzenie tabeli Klasa
CREATE TABLE Klasa(
	ID_Klasy int unsigned NOT NULL AUTO_INCREMENT,
	Wychowawca int unsigned NOT NULL,
	Rocznik int unsigned NOT NULL,
	Oddzial varchar(3) NOT NULL,
	profil varchar(40),
	liczebnosc int unsigned NOT NULL,
	PRIMARY KEY(ID_Klasy)
);
--Utworzenie tabeli Przedmiot
CREATE TABLE Przedmiot(
	ID_Przedmiotu int unsigned NOT NULL AUTO_INCREMENT,
	Nazwa varchar(40) NOT NULL,
	PRIMARY KEY(ID_Przedmiotu)
);
--Utworzenie tabeli Adres
CREATE TABLE Adres(
	ID_Adresu int unsigned NOT NULL AUTO_INCREMENT,
	Miejscowosc VARCHAR(40) NOT NULL,
	KodPocztowy VARCHAR(6) NOT NULL,
	Ulica VARCHAR(40),
	nrDomu int unsigned NOT NULL,
	nrMieszkania int unsigned,
	PRIMARY KEY(ID_Adresu)
);
--Utworzenie tabeli Zachowanie
CREATE TABLE Zachowanie(
	nrLegitymacjiUcznia VARCHAR(11) NOT NULL,
	PunktyZachowania int,
	UNIQUE(nrLegitymacjiUcznia)
);
--Utworzenie tabeli Uwaga
CREATE TABLE Uwaga(
	ID_Uwagi int unsigned NOT NULL AUTO_INCREMENT,
	nrLegitymacjiUcznia varchar(10) NOT NULL,
	ID_Nauczyciela int unsigned NOT NULL,
	OdjetePunkty int NOT NULL,
	Komentarz varchar(500),
	PRIMARY KEY(ID_Uwagi)
);
--Utworzenie tabeli JednostkaLekcyjna
--Dodano juz wszystkie tabele, ktore nie sa pomostowe, wiec
--od razu w definicji tabeli umieszczamy relacje
CREATE TABLE JednostkaLekcyjna(
	ID_Nauczyciela int unsigned NOT NULL,
	ID_Klasy int unsigned NOT NULL,
	ID_Przedmiotu int unsigned NOT NULL,
	FOREIGN KEY (ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela),
	FOREIGN KEY (ID_Klasy) REFERENCES Klasa(ID_Klasy),
	FOREIGN KEY (ID_Przedmiotu) REFERENCES Przedmiot(ID_Przedmiotu)
);
--Utworzenie OpiekunUcznia
CREATE TABLE OpiekunUcznia(
	nrLegitymacjiUcznia varchar(11) NOT NULL,
	ID_Opiekuna int unsigned NOT NULL,
	FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(NrLegitymacji),
	FOREIGN KEY(ID_Opiekuna) REFERENCES Opiekun(ID_Opiekuna)
);
--dodawanie relacji dla tabeli Uczen
ALTER TABLE Uczen
ADD FOREIGN KEY (ID_Klasy) REFERENCES Klasa(ID_Klasy);
ALTER TABLE Uczen
ADD FOREIGN KEY (ID_Adresu) REFERENCES Adres(ID_Adresu); 
--dodawanie relacji dla tabeli Nauczyciel
ALTER TABLE Nauczyciel
ADD FOREIGN KEY(ID_Adresu) REFERENCES Adres(ID_Adresu);
--dodawanie relacji dla tabeli Administrator
ALTER TABLE Administrator
ADD FOREIGN KEY(ID_Adresu) REFERENCES Adres(ID_Adresu);
--dodawanie relacji dla Opiekuna
ALTER TABLE Opiekun
ADD FOREIGN KEY(ID_ADresu) REFERENCES Adres(ID_Adresu);
--dodawanie relacji dla Ocena
ALTER TABLE Ocena
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji);
ALTER TABLE Ocena
ADD FOREIGN KEY(ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela);
ALTER TABLE Ocena
ADD FOREIGN KEY(ID_Przedmiotu) REFERENCES Przedmiot(ID_Przedmiotu);
--dodawanie relacji dla Klasa
ALTER TABLE Klasa
ADD FOREIGN KEY(Wychowawca) REFERENCES Nauczyciel(ID_Nauczyciela);
--dodawanie relacji dla Zachowania
ALTER TABLE Zachowanie
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji);
--dodawanie relacji dla Uwaga
ALTER TABLE Uwaga
ADD FOREIGN KEY(nrLegitymacjiUcznia) REFERENCES Uczen(nrLegitymacji);
ALTER TABLE Uwaga
ADD FOREIGN KEY(ID_Nauczyciela) REFERENCES Nauczyciel(ID_Nauczyciela);















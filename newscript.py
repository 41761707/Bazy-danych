import mysql.connector
from mysql.connector import Error
import random
from datetime import date
#UWAGA: jest to bardzo primitywna wersja skryptu, jednak wykonuje swoją robotę
#istnieje szansa, że zostanie on udoskonalony jeśli wystarczy nam czasu
#UWAGA: wszystkie wygenerowane w tym pliku pesele nie spelniają założeń
#odnośnie poprawności PESELI
def name_generator():
    name=SampleNames[random.randint(0,len(SampleNames)-1)]
    return name
def surrname_generator(name):
    surrname=""
    if(name[len(name)-1]=='a'):
        surrname=SampleSurrnamesWM[random.randint(0,len(SampleSurrnamesWM)-1)]
    else:
        surrname=SampleSurrnamesM[random.randint(0,len(SampleSurrnamesM)-1)]
    return surrname
def PESEL_generator():
    PESEL=""
    i=0;
    while(i<11):
        PESEL=PESEL+str(random.randint(0,9))
        i=i+1
    return PESEL
def phone_generator():
    phoneNumber=""
    i=0
    while(i<9):
        phoneNumber=phoneNumber+str(random.randint(0,9))
        i=i+1
    return phoneNumber
def email_generator(name,surrname):
    email=name+"."+surrname+"@szkola.com"
    return email
def legitymacja_generator(i):
    nrLegitymacji=str(i)+"/2021"
    return nrLegitymacji
try:
    connection = mysql.connector.connect(host='localhost',
                                         database='dziennik2',
                                         user='admin',
                                         password='admin')
    cursor = connection.cursor()
    liczbaAdresow=100
    liczbaNauczycieli=30
    liczbaUczniow=120
    liczbaPrzedmiotow=10
    #dla ułatwienia generowania danych jeden opiekun-jeden uczeń
    liczbaOpiekunow=120
    liczbaKlas=4
    liczbaOcen=1000
    liczbaUwag=100
    SampleNames=['Dominik',
                 'Henryk',
                 'Mieszko',
                 'Czesław',
                 'Julian',
                 'Konrad',
                 'Eryk',
                 'Jakub',
                 'Mirosław',
                 'Radosław',
                 'Paweł',
                 'Grzegorz',
                 'Filip',
                 'Kacper',
                 'Marek',
                 'Marcel',
                 'Ryszard',
                 'Jan',
                 'Mariusz',
                 'Bartłomiej',
                 'Natasza',
                 'Helena',
                 'Ewelina',
                 'Karloina',
                 'Marcelina',
                 'Magdalena',
                 'Agata',
                 'Iga',
                 'Aneta',
                 'Katarzyna',
                 'Natalia',
                 'Wiktoria',
                 'Weronika',
                 'Oliwia',
                 'Paulina',
                 'Julia',
                 'Kaja',
                 'Małgorzata',
                 'Urszula',
                 'Sylwia']
    SampleSurrnamesM=['Woźniak',
                     'Zalewski',
                     'Brzeziński',
                     'Andrzejewski',
                     'Wiśniewski',
                     'Ostrowski',
                     'Jasiński',
                     'Walczak',
                     'Przybylski',
                     'Wróblewski',
                     'Kozłowski',
                     'Lewandowski',
                     'Baran',
                     'Kaźmierczak',
                     'Kołodziej',
                     'Krawczyk',
                     'Mróz',
                     'Marciniak',
                     'Cieślak',
                     'Wojciechowski',
                     'Piotrkowski',
                     'Nowak',
                     'Sikorski',
                     'Duda',
                     'Kwolik']
    SampleSurrnamesWM=['Woźniak',
                      'Zalewska',
                      'Brzezińska',
                      'Andrzejewska',
                      'Wiśniewska',
                      'Ostrowska',
                      'Jasińska',
                      'Walczak',
                      'Przybylska',
                      'Wróblewska',
                      'Kozłowska',
                      'Lewandowska',
                      'Baran',
                      'Kaźmierczak',
                      'Kołodziej',
                      'Krawczyk',
                      'Mróz',
                      'Marciniak',
                      'Cieślak',
                      'Wojciechowska',
                      'Piotrkowska',
                      'Nowak',
                      'Sikorska',
                      'Duda',
                      'Kwolik']
    SampleCities=['Warszawa',
                  'Kraków',
                  'Łódź',
                  'Wrocław',
                  'Poznań',
                  'Gdańsk',
                  'Szczecin',
                  'Bydgoszcz',
                  'Białystok',
                  'Katowice',
                  'Opole',
                  'Lublin',
                  'Głogów',
                  'Grudziądz',
                  'Leszno',
                  'Lipno',
                  'Oława',
                  'Jelcz-Laskowice']
    SampleStreets=['Polna',
                   'Malinowa',
                   'Wrocławska',
                   'Poznańska',
                   'Kolejowa',
                   'Motornicza',
                   'Morelowa',
                   'Willowa',
                   'Brzozowa',
                   'Kwiatowa',
                   'Dworcowa',
                   'Belgijska',
                   'Bema',
                   'Bociania',
                   'Boiskowa',
                   'Malownicza',
                   'Marchewkowa']
    SampleSubjects=['matematyka',
                    'j.polski',
                    'j.angielski',
                    'j.niemiecki',
                    'biologia',
                    'chemia',
                    'fizyka',
                    'informatyka',
                    'historia',
                    'geografia']
    SampleProfiles=['matematyczna',
                    'biologiczno-chemiczna',
                    'fizyczna',
                    'sportowa']
    #adresy
    i=0
    while(i<liczbaAdresow):
       City=SampleCities[random.randint(0, len(SampleCities)-1)]
       PostalCode=""
       j=0
       while(j<5):
           if(j==2):
               PostalCode=PostalCode+"-"
           PostalCode=PostalCode+str(random.randint(0,9))
           j=j+1
       Street=SampleStreets[random.randint(0,len(SampleStreets)-1)]
       HomeNumber=random.randint(0,50)
       FlatNumber=random.randint(0,10)
       args=[City,PostalCode,Street,HomeNumber,FlatNumber]
       cursor.callproc('dodaj_adres', args)
       connection.commit()
       i=i+1
    #przedmiot
    for x in range(len(SampleSubjects)):
        args=(SampleSubjects[x],)
        cursor.callproc('dodaj_przedmiot',args)
        connection.commit()
    #administrator- tutaj dodaję z góry ustalone dwie osoby w przykładzie
    administrator1=["Radek",
                    "Wojtczak",
                    "01122334455",
                    random.randint(1,liczbaAdresow),
                    "504016627",
                    "radek.wojtczak@szkola.com",
                    "Radek",
                    "Wojtczak"]
    administrator2=["Pawel",
                    "Zalewski",
                    "55443322110",
                    random.randint(1,liczbaAdresow),
                    "726610405",
                    "pawel.zalewski@szkola.com",
                    "Pawel",
                    "Zalewski"]
    cursor.callproc('dodaj_administratora',administrator1)
    connection.commit()
    cursor.callproc('dodaj_administratora',administrator2)
    connection.commit()
    #nauczyciele
    nauczyciel=0
    while(nauczyciel<liczbaNauczycieli):
        name=name_generator()
        surrname=surrname_generator(name)
        PESEL=PESEL_generator()
        phone=phone_generator()
        email=email_generator(name,surrname)
        login=name
        password=surrname
        args=[random.randint(1,liczbaAdresow),
              nauczyciel+1,
              name,
              surrname,
              PESEL,
              phone,
              email,
              login,
              password]
        cursor.callproc('dodaj_nauczyciela',args)
        connection.commit()     
        nauczyciel=nauczyciel+1
    #klasy
    klasaA=[random.randint(1,liczbaNauczycieli),2,"A",SampleProfiles[0],0]
    klasaB=[random.randint(1,liczbaNauczycieli),2,"B",SampleProfiles[1],0]
    klasaC=[random.randint(1,liczbaNauczycieli),2,"C",SampleProfiles[2],0]
    klasaD=[random.randint(1,liczbaNauczycieli),2,"D",SampleProfiles[3],0]
    cursor.callproc('dodaj_klase',klasaA)
    connection.commit()  
    cursor.callproc('dodaj_klase',klasaB)
    connection.commit()
    cursor.callproc('dodaj_klase',klasaC)
    connection.commit()
    cursor.callproc('dodaj_klase',klasaD)
    connection.commit()
    #Uczen
    uczen=0
    while(uczen<liczbaUczniow):
        nrLegitymacji=legitymacja_generator(uczen)
        name=name_generator()
        surrname=surrname_generator(name)
        address=random.randint(1,liczbaAdresow)
        classID=random.randint(1,liczbaKlas)
        PESEL=PESEL_generator()
        phoneNumber=phone_generator()
        Email=email_generator(name,surrname)
        Login=name
        Password=surrname
        args=[nrLegitymacji,name,surrname,address,classID,PESEL,phoneNumber,Email,Login,Password]
        connection.commit()
        cursor.callproc('dodaj_ucznia',args)
        connection.commit()
        uczen=uczen+1
    opiekun=0
    while(opiekun<liczbaOpiekunow):
        name=name_generator()
        surrname=surrname_generator(name)
        address=random.randint(1,liczbaAdresow)
        PESEL=PESEL_generator()
        phoneNumber=phone_generator()
        Email=email_generator(name,surrname)
        Login=name
        Password=surrname
        args=[name,surrname,address,PESEL,phoneNumber,Email,Login,Password]
        connection.commit()
        cursor.callproc('dodaj_opiekuna',args)
        connection.commit()
        opiekun=opiekun+1
    ocena=0
    while(ocena<liczbaOcen):
        nrLegitymacjiUcznia=legitymacja_generator(random.randint(0,liczbaUczniow-1))
        TeacherID=random.randint(1,liczbaNauczycieli)
        SubjectID=random.randint(1,liczbaPrzedmiotow)
        today = date.today()
        Date = today.strftime("%Y-%m-%d")
        Note=random.randint(1,6)
        Comment=""
        args=[nrLegitymacjiUcznia,TeacherID,SubjectID,Date,Note,Comment]
        cursor.callproc('dodaj_ocene',args)
        connection.commit()
        ocena=ocena+1
    #zachowanie
    zachowanie=0
    while(zachowanie<liczbaUczniow):
        nrLegitymacjiUcznia=legitymacja_generator(zachowanie)
        points=100
        args=[nrLegitymacjiUcznia,points]
        cursor.callproc('dodaj_zachowanie',args)
        connection.commit()
        zachowanie=zachowanie+1
    #uwagi
    uwaga=0
    while(uwaga<liczbaUwag):
        nrLegitymacjiUcznia=legitymacja_generator(random.randint(0,liczbaUczniow-1))
        TeacherID=random.randint(1,liczbaNauczycieli)
        points=random.randint(-10,-1)
        Comment=""
        args=[nrLegitymacjiUcznia,TeacherID,points,Comment]
        cursor.callproc('dodaj_uwage',args)
        connection.commit()
        uwaga=uwaga+1
    #dodanie jednostek lekcyjnych
    for i in range(liczbaKlas):
        for j in range(liczbaPrzedmiotow):
            TeacherID=random.randint(1,liczbaNauczycieli)
            ClassID=i+1
            SubjectID=j+1
            args=[TeacherID,ClassID,SubjectID]
            cursor.callproc('dodaj_lekcje',args)
            connection.commit()
   #dodanie opiekunow dla uczniow, dla ulatwienia 1 opiekun jeden uczen
    laczenie=0
    while(laczenie<liczbaUczniow):
        Legitymacja=legitymacja_generator(laczenie)
        OpiekunID=laczenie+1
        args=[Legitymacja,OpiekunID]
        cursor.callproc('dodaj_relacje',args)
        connection.commit()
        laczenie=laczenie+1
except mysql.connector.Error as error:
    print("Failed to execute stored procedure: {}".format(error))

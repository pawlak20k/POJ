# POJ

Aplikacja z interfejsem GUI napisana w Java na potrzeby projektu POJ.
Aktualna wersja: v1.1

# Wersja v1.1
- dodanie zapisywanie zawartości do pliku "wyniki.txt" (w formie logów)
- format jaki obowiązuje (przykład):
a) [ID: 763664][2023-06-03 15:33:03][BMI: 22,31 (Prawidłowa) Waga: 78.0 Wzrost: 187.0]
b) [ID: 282497][2023-06-03 15:33:07][Zapotrzebowanie kaloryczne: 2270,77 kcal, Średnia aktywność, Wiek: 67]
- informacje są zapisywane z dwóch zakładek: Kalkulator BMI, Kalkulator Kalorii

# Wersja: v1.0
Aplikacja będzie mieć funkcjonalności takie jak:
- trzy zakładki: Kalkulator BMI, Kalkulator Kalorii, O programie
- w zakładce Kalkulator BMI użytkownik podaje takie wartości jak waga, wzrost i na tej podstawie wyliczane jest BMI. Na dole również będzie znajdować się zakres BMI z czterema kategoriami: Niedowaga, Prawdłowa, Nadwaga, Otyłość. W tej zakładce aplikacja wyliczy BMI użytkownika używając wzoru waga/wzrost^2
- w zakładce Kalkulator Kalorii użytkownik po wcześniejszym podaniu wagi oraz wzrostu będzie proszony o wiek oraz płeć, ale również z listy rozwijanej będzie mieć możliwość wyboru aktywności fizycznej. Do wyboru ma cztery kategorie: brak aktywności, niska aktywność, średnia aktywność, wysoka aktywność. W tej zakładce specjalny algorytm biorąc pod uwage dane, które użytkownik podał policzy dzienne zapotrzebowanie kaloryczne (kcal)
- w zakładce O programie będzie znajdować się krótka notka o wersji aplikacji oraz jej autorze.

# Interfejs GUI

<img width="438" alt="1Poj" src="https://github.com/pawlak20k/POJ/assets/115339814/d57b6092-ca12-42e0-83af-3f05eaabdc14">

<img width="438" alt="2Poj" src="https://github.com/pawlak20k/POJ/assets/115339814/a00aedea-6659-4a39-ad7c-9167e0e45deb">

<img width="438" alt="3Poj" src="https://github.com/pawlak20k/POJ/assets/115339814/4dc77ec8-79e6-4bd4-a2d6-888d7a90658b">

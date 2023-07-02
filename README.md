# Backend für Webshop

Dies ist der Backend-Teil des Webshops, der mit Postgres, Jakarta EE, Spring Boot entwickelt wurde. Hier finden Sie eine kurze Anleitung zur Einrichtung und Verwendung des Backends.

## Voraussetzungen

Stellen Sie sicher, dass die folgenden Komponenten auf Ihrem System installiert sind:

- Intellij IDE (oder ähnliches)

- Docker

- Java Development Kit (JDK) 8 oder höher

## Installation

1\. Klone das Repository auf deinen lokalen Computer:


    git clone https://git.ffhs.ch/nina.aegerter/jea_semesterprojekt_gruppea.git

  

2\. Navigiere in das Verzeichnis des Backend-Teils:

    cd jea_semesterprojekt_gruppea


3\. Öffne das Projekt in deiner bevorzugten Java-IDE.


4\. Starte die Datenbank mit Docker Compose:

    /infrastructure/docker-compose.yml

   
Dadurch wird ein Docker-Container für die Postgres-Datenbank erstellt und gestartet.

5\. Nachdem das Docker-Compose-File gestartet wurde, kann man auf Docker prüfen, ob das Datenbank-Image erfolgreich angelegt wurde.    

Danach kann man im Database-Toolfenster die Datenbank-Eigenschaften ansehen und ändern. Im Dialogfeld folgende Eigenschaften angeben, um auf die Datenbank zuzugreifen. Als Port Nummer wurde der Postgres-Standard Port 5432 verwendet. Zudem müssen noch die User- und Passwort-Angaben hinzugefügt werden.  (User und Passwort = postgres)


6\. Konfiguration der Datenbankverbindung:

   - Öffne die Datei 

    src/main/resources/application.properties

   - Passe die Eigenschaften an Postgres-Datenbank anzugeben.

    spring.datasource.url
---
    spring.datasource.username 
---
    spring.datasource.password
---
    jdbc:postgresql://localhost:5432/postgres
---
    user = postgres 
---
    password = postgres

 um die Verbindungsinformationen für deine 


7\. Starte die Anwendung:

   - Führe die `main`-Methode aus.

    src/main/java/ch/ffhs/customer/customer/CustomerApplication

   - Die Spring Boot-Anwendung wird gestartet und bindet sich standardmäßig an den Port 8020/shop.

8\. Starte die Anwendung:

   - Führe die `main`-Methode aus

    src/main/java/ch/ffhs/library/library/LibraryApplication

9\. Starte die Anwendung:

   - Führe die `main`-Methode aus.

    src/main/java/ch/ffhs/admin/admin/AdminApplication

   - Die Spring Boot-Anwendung wird gestartet und bindet sich standardmäßig an den Port 8019/admin.

10\. Überprüfe, ob die Backend-Anwendung erfolgreich gestartet wurde, indem du 

    http://localhost:8020/shop/swagger

in deinem Webbrowser aufrufst.

## Kundenanwendung

Die Kundenanwendung kann unter 

    http://localhost:8020/shop 
    
aufgerufen werden. Hier können Kunden Produkte anzeigen und kaufen.

- `GET /products`: Ruft alle Produkte im Webshop ab.

- `GET /products/{id}`: Ruft ein bestimmtes Produkt anhand der angegebenen ID ab.

- `GET /products-in-category/{id}`: Rufe Produkte in einer Kategory ab.

- `GET /category`: Ruft alle Kategory im Webshop ab.

- `GET /category/{id}`: Ruft ein bestimmtes Kategory anhand der angegebenen ID ab.

Diese Endpunkte können über den entsprechenden Vue.js-Frontend-Teil des Webshops aufgerufen und genutzt werden.

## Administrationsoberfläche

Die Admin-Anwendung kann unter `http://localhost:8019/admin` aufgerufen werden. Hier können Produkte erstellt, aktualisiert und gelöscht werden.

- `POST /add-product`:Erstelle Produkte im Webshop.

- `PUT /update-product/{id}`: Ein Produkt verändern.

- `DELETE /delete-product/{id}`: Lösche ein Produkt.

- `POST /add-category`:Erstelle Kategory im Webshop.

- `PUT /update-category/{id}`: Ein Kategory verändern.

- `DELETE /delete-category/{id}`: Lösche ein Kategory.

## Dokumentation

    
    https://git.ffhs.ch/nicolas.dreier/jeafrontend.git

    

## Weitere Informationen

Für weitere Informationen und eine detaillierte API-Dokumentation können Sie das Wiki dieses Repositories konsultieren oder den Code direkt durchsuchen.


# Backend für Webshop

Dies ist der Backend-Teil des Webshops, der mit Postgres, JPA, Spring Boot und Vue.js entwickelt wurde. Hier finden Sie eine kurze Anleitung zur Einrichtung und Verwendung des Backends.

## Voraussetzungen

Stellen Sie sicher, dass die folgenden Komponenten auf Ihrem System installiert sind:

- Docker

- Docker Compose

- Java Development Kit (JDK) 8 oder höher

## Installation

1\. Klone das Repository auf deinen lokalen Computer:

   ```

   git clone https://git.ffhs.ch/nina.aegerter/jea_semesterprojekt_gruppea.git

   ```

2\. Navigiere in das Verzeichnis des Backend-Teils:

   ```

   cd jea_semesterprojekt_gruppea

   ```

3\. Starte die Datenbank mit Docker Compose:

   ```

   /infrastructure/docker-compose.yml

   ```

   Dadurch wird ein Docker-Container für die Postgres-Datenbank erstellt und gestartet.

4\. Öffne das Projekt in deiner bevorzugten Java-IDE.

5\. Konfiguration der Datenbankverbindung:

   - Öffne die Datei `src/main/resources/application.properties`.

   - Passe die Eigenschaften `spring.datasource.url`, `spring.datasource.username` und `spring.datasource.password` an, um die Verbindungsinformationen für deine Postgres-Datenbank anzugeben. Standardmäßig ist die URL auf `jdbc:postgresql://localhost:5432/postgres` eingestellt mit user = postgres und password = postgres.


6\. Starte die Anwendung:

   - Führe die `main`-Methode der Klasse `src/main/java/ch/ffhs/customer/customer/CustomerApplication` aus.

   - Die Spring Boot-Anwendung wird gestartet und bindet sich standardmäßig an den Port 8020/shop.

7\. Starte die Anwendung:

   - Führe die `main`-Methode der Klasse `src/main/java/ch/ffhs/admin/admin/AdminApplication` aus.

   - Die Spring Boot-Anwendung wird gestartet und bindet sich standardmäßig an den Port 8019/admin.

8\. Überprüfe, ob die Backend-Anwendung erfolgreich gestartet wurde, indem du `http://localhost:8020/shop/swagger` in deinem Webbrowser aufrufst.

## Kundenanwendung

Die Kundenanwendung kann unter `http://localhost:8020/shop` aufgerufen werden. Hier können Kunden Produkte anzeigen und kaufen.

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


## Weitere Informationen

Für weitere Informationen und eine detaillierte API-Dokumentation können Sie das Wiki dieses Repositories konsultieren oder den Code direkt durchsuchen.


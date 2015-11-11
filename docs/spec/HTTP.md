Spezifikation: HTTP API
=======================

Generell
--------

*   METHOD /path HTTP/1.1

Benutzerfunktionen
------------------

### `POST /leruka/register` ###

Header:

    Accept: application/json
    Content-type: application/json
    Charset: utf-8

Inhalt:

Ein JSON-Objekt folgender Form:

    {
      "userName": "Max",
      "passwordHash": "973ed5b8f3b4a8b326a74303ed24e297d2d5c40f9c2a9a011880598b68f9b185"
    }

*   `userName`: Der gewünschte Benutzername des neuen Benutzers
*   `passwordHash`: Das in SHA-2 (256) verschlüsselte Passwort des neuen Benutzers

Rückgabe:

Ein JSON-Objekt. Bei Erfolg z.B.:

    {
      "success": true,
      "sessionID": "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
    }

Die Zeichenkette ist eine gültige UUID.

Bei einem Fehler wird kein Inhalt zurück gegeben, sondern nur ein entsprechender Status Code gesetzt.

Mögliche Fehler:

tbd

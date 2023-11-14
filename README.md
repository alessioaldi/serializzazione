# chatRoom
## Protocollo Server
Il protocollo del server inizia chiedendo l'indirizzo IP .
successivamente chiede lo username.
**Non possono esserci più client con lo stesso nome**
---
per la comunicazione privata va inserito il nome di un client preceduto da "/" e scritto successivamente il messaggio.
### es. "/client" *INVIO*
###     "*messaggio*" *INVIO*
Dopo aver inviato un messaggio privato si torna alla modalità broadcast.
---
Per l'uscita dalla comunicazione va inserito "/q" o "/Q".

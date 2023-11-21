# chatRoom - Protocollo Server
Il protocollo del server inizia chiedendo l'indirizzo IP a cui collegarsi, successivamente chiede lo username.
**Non possono esserci più client con lo stesso nome.**
Ogni utente deve avere un thread di ascolto che resta in ascolto sul server.
## Comunicazione Broadcast
Ogni messaggio inviato verrà ricevuto da tutti i clienti connessi.
Per la comunicazione in modalità privata va inserito il nome di un client preceduto da "/" e scrivere successivamente il messaggio.
Un'esempio:
### "/client" --- * *INVIO**
### "messaggio" --- * *INVIO* *
**Dopo aver inviato un messaggio privato si torna alla modalità broadcast.**
Per l'uscita dalla comunicazione va inserito "/q" o "/Q".

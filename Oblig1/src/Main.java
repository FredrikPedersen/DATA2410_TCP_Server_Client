import URLReading.*;

/**
 * Ser for meg at koden kan optimaliseres på en måte, slik at vi ikke tar inn alle jævla verdiene hver gang.
 *
 * Uavhengig av det, så blir veien her videre å få koden til å aksessere riktig side på worldtimeserver, også å
 * hente og formatere dataene derifra. Burde ikke bli altfor mye jobb nå som man først har fått til å hente data
 * i utgangspunktet.
 *
 * ***************************************************************************************************************************
 *
 * Ideer:
 * Ta ønsket land som input. Sublistene i RegionList ligger allerede sortert, så et binærsøk burde bli mest effektivt
 * for å finne riktig element. Når man finner riktig element, typ Land = x.get(1).get(i), så vil det fungere å hente
 * ut regionkoden via x.get(0).get(i).
 *
 * Burde være mulig å effektivisere koden ytterligere ved bruk av arrays i stedet for arraylist. Dette kan være hensiktsmessig
 * siden mengden land som skal inn i listen er konstant. Det vil dermed kreve at koder en del ekstra, og burde ikke prioriteres
 * ved mindre den nåværende løsningen viser seg å bli for treig.
 *
 */

public class Main {

    public static void main(String[] args) throws Exception {
        URLReader.reader();
    }
}
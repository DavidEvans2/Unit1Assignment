package Unit1ParkingApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileInput {

    public void getAllIds(List<Ticket> tickets) throws IOException {
        try{
            BufferedReader brFile = new BufferedReader(new FileReader("Tickets.txt"));
            String line = brFile.readLine();
            while (line != null){
                String[] splitStr = line.split(": ");
                Ticket newTicket = new Ticket(Integer.parseInt(splitStr[1]));
                tickets.add(newTicket);
                line = brFile.readLine();
            }
            brFile.close();
        }catch(FileNotFoundException e){
            System.out.println("Hmm, says File Not Found Boss. Maybe you typed the filename wrong?.");
        }catch(IOException e){
            System.out.println("IOException thrown. Maybe you wanna change somethin around?");
        }
    }
}
package Unit1ParkingApp;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOutput {

    public void addTicketsToFile(List<Ticket> tickets) throws FileNotFoundException, IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("Tickets.txt"));
        for(int i = 0; i < tickets.size(); i++){
            out.write("Ticket id: " + tickets.get(i).getId() + "\n");
        }
        out.close();
    }
}
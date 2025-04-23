package txtFileManager;

import Common.Commons;
import Common.Room;
import Manager.RoomManager;

public class Main {

	public static void main(String[] args) {
		
		 
        Room b = new Room();  
        b.setRoomNumber(7);
        b.setStatus("Available");
        b.setGuestsName("None");
        b.setBed("4");
        
        RoomManager bm = new RoomManager();  
        
        bm.saveOrUpdateRoom(b); 
        
        bm.DeleteRoom(5); 
        
        System.out.println(bm.return_information(4));  // چاپ اطلاعات اتاق شماره 4

        // کدهای مربوط به txtfilemanager
        txtfilemanager x = new txtfilemanager("C:\\Users\\Windows11\\eclipse-workspace\\Hotel\\HOTEL.txt");
        x.CreateFile();
        x.AppendRow("salam,stgtbrth,Available");
        x.AppendRow("this is a hotel txtfile ");
        x.AppendRow("bye");

        x.updateRow(3,"3|Saman aslani|1570805830|09222684928");
      
        System.out.println(".....");
    }

}
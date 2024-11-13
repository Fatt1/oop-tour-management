/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IOFile;

import interfaces.LoadData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import model.tour.DomesticTour;
import model.tour.InternationalTour;
import model.tour.Tour;

/**
 *
 * @author nghialam
 */
public class LoadTourFromFileText implements LoadData {

    private String fName;

    public LoadTourFromFileText(String fname) {
        this.fName = fname;
    }

    @Override
    public Object[] read() {
        String filePath = fName;  // Đường dẫn tới file tour.txt
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Tour[] tour = new Tour[0];
            int count = 0;
            boolean isFristLine = true;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");  // Tách các trường trong mỗi dòng
                if(isFristLine){
                    isFristLine = false;
                    continue;
                }
                 if (parts.length < 11) continue; // Đảm bảo đủ 11 trường
                String type = parts[1].trim(); // Loại tour (International/Domestic)
                String tourID = parts[2].trim();
                String tourName = parts[3].trim(); // Tên tour
                String destination = parts[4].trim(); // Điểm đến
                String departureLocal = parts[5].trim(); // Số ngày (int)
                String vehicle = parts[6].trim();
                int price = Integer.parseInt(parts[7].trim());
                int quantity = Integer.parseInt(parts[8].trim());
                String country = null, visa = null;
                double discount = 0;
                if(!parts[9].trim().isEmpty()){
                    country = parts[9].trim();
                }
                if(!parts[10].trim().isEmpty()){
                visa = parts[10].trim();
                }
                
                if(!parts[11].trim().isEmpty()){
                    discount = Double.parseDouble(parts[11].trim());
                }
                
                if (type.equals("International Tour")) {
                    tour = Arrays.copyOf(tour, tour.length + 1);
                    tour[count++] = new InternationalTour(country, visa, tourID, tourName, destination, departureLocal, vehicle, price, quantity);
                } else if (type.equals("Domestic Tour")) {
                    tour = Arrays.copyOf(tour, tour.length + 1);
                    tour[count++] = new DomesticTour(discount, tourID, tourName, destination, departureLocal, vehicle, price, quantity);
                }
            }
            return tour;
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return null;
    }

}

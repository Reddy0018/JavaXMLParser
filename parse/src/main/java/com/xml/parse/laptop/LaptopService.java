package com.xml.parse.laptop;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    public void saveLaptopsFromXML(){
        List<Laptop> laptops = ParseLaptopXml.executeLaptop();
        System.out.println("Laptops: " + laptops);
        laptopRepository.saveAll(laptops);
    }

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Map<String, Object> saveLaptops(List<Laptop> laptops) {
        Map<String,Object> status = new HashMap<>();
        try {
            laptopRepository.saveAll(laptops);
        }catch (Exception e){
            status.put("Status","Failed Saving provided objects");
            return status;
        }
        status.put("status","Success");
        status.put("laptopObjectsSaved", laptops);
        return status;
    }

    @Transactional
    public Map<String, Object> deleteLaptopByID(Long id) {
        Map<String,Object> status = new HashMap<>();
        status.put("laptopObjectId", id);
        try {
            Laptop laptop = laptopRepository.findById(id).orElseThrow(()-> new IllegalStateException("Found zero elements with provided ID"));
            laptopRepository.delete(laptop);
        } catch (Exception e){
            status.put("Status",e.getMessage());
            return status;
        }
        status.put("status","Success");
        return status;
    }

    @Transactional
    public Map<String,Object> updateLaptop(Long id, String ssd, Integer price, String hardDrive) {
        Map<String,Object> status = new HashMap<>();
        Laptop laptop = laptopRepository.findById(id).orElseThrow(()-> new IllegalStateException("Found zero elements with provided ID"));
        if(StringUtils.isNotBlank(ssd)){
            laptop.setSsd(ssd);
        }
        if(StringUtils.isNotBlank(hardDrive)){
            laptop.setHardDrive(hardDrive);
        }
        if(null!=price){
            laptop.setPrice(price);
        }
        status.put("status","Success");
        status.put("updateLaptopObject",laptop);
        return status;
    }
}

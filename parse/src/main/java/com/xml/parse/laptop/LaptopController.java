package com.xml.parse.laptop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    @PostMapping(path = "/saveXML")
    public String saveXMLLaptopData(){
        laptopService.saveLaptopsFromXML();
        return "Success";
    }

    @GetMapping(path = "/getAllLaptops")
    public List<Laptop> getAllLaptops(){
        return laptopService.getAllLaptops();
    }

    @PostMapping(path = "/saveLaptops")
    public Map<String,Object> saveLaptops(@RequestBody List<Laptop> laptops){
        return laptopService.saveLaptops(laptops);
    }

    @DeleteMapping("deleteById/{id}")
    public Map<String,Object> deleteLaptopbyId(@PathVariable("id") Long id){
        return laptopService.deleteLaptopByID(id);
    }

    @PutMapping("/update/{id}")
    public Map<String,Object> updateLaptopByID(@PathVariable("id") Long id, @RequestParam(required = false) String ssd,
                @RequestParam(required = false) Integer price, @RequestParam(required = false) String hardDrive){
        return laptopService.updateLaptop(id,ssd,price,hardDrive);
    }
}

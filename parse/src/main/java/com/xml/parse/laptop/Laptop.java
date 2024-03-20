package com.xml.parse.laptop;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.Field;

@Entity(name = "laptop")
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Laptop {

    @Id
    @SequenceGenerator(name = "laptop_seq", sequenceName = "laptop_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laptop_seq")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "ram", columnDefinition = "TEXT")
    private String ram;
    @Column(name = "hard_drive", columnDefinition = "TEXT", nullable = true)
    private String hardDrive;
    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;
    @Column(name = "ssd", columnDefinition = "TEXT", nullable = true)
    private String ssd;

    public static Field[] getDeclaredFeilds() {
        return Laptop.class.getDeclaredFields();
    }

    public static void setField(String name, String value, Laptop laptop){
        PropertyAccessor propertyAccessor = PropertyAccessorFactory.forBeanPropertyAccess(laptop);
        propertyAccessor.setPropertyValue(name,value);
    }


}

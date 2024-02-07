package site.devdalus.ariadne.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @Builder
    public Basic(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void testSetName(String name) {
        this.name = name;
    }

}

package hello.hellospring.domain;

import org.springframework.beans.factory.config.BeanDefinition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 데이터를 저장할 때 구분을 위해 시스템이 정하는 임의의 값
    private String name; // 사용자가 직접 입력하는 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

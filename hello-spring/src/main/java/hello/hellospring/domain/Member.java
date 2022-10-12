package hello.hellospring.domain;

public class Member {

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

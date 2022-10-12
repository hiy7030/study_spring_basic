package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 웹 서버에 실행되는 모델??
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // url-path
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // mvc 방식
    @GetMapping("hello-mvc") // RequestParam 애너테이션 : 외부에서 파라미터를 받는다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // api 방식
    @GetMapping("hello-string")
    @ResponseBody // http의 바디 부분에 데이터를 사용자가 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 바디 부분에 넣을 데이터=name
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 넘김 -> 실행 결과 : {"name":"Spring"} JSON 구조로 나옴
    }

    static class Hello { // 정적클래스 -> 클래스 안에서 클래스를 쓸 수 있음
        private String name;

        public String getName() {  // 자동완성 alt+insert
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

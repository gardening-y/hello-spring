package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

//    web application에서 hello로 들어오면 이 method를 호출
    @GetMapping("hello")
    public String hello(Model model){
//        data로 hello!!!!!를 넘길 것 html에서 ${data}로 가능
        model.addAttribute("data", "hello!!!!!");
//        resources/templates/hello.html에 연결
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
//    html 없이 data가 그대로 넘어감
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
//    RequestParam("name") 이면 hello-string?name=어쩌구 라는 url로 들어가야 함
//    required가 default로 true여서 ~/hello-api url로 들어가면 오류 남
//    public Hello helloApi(@RequestParam(value = "name", required = false) String name) {
        return "hello " + name; //"hello string"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}

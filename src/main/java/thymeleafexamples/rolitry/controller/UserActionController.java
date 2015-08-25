package thymeleafexamples.rolitry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @Scope("prototype")
@RequestMapping("/user")
public class UserActionController<V> {
  @RequestMapping(value = "/success", method = RequestMethod.GET)
  public ModelAndView save(String name1, String password1) {
    name1 = "李荣";
    System.out.println("接收到的信息:" + name1);
    ModelAndView mov = new ModelAndView();
    mov.setViewName("succ2");
    mov.addObject("name", name1);
    mov.addObject("passwd", password1);
    mov.addObject("msg", "保存成功");

    return mov;
  }

  @RequestMapping(value = "/success2", method = RequestMethod.POST)
  public String helloWorld(String name2, String password2, Model model) {
    System.out.println("接收到的用户信息：" + name2);
    model.addAttribute("name", name2);
    model.addAttribute("passwd", password2);
    model.addAttribute("msg", "保存成功了!???!!");
    return "succ";
  }

  @RequestMapping(value = "/ajaxshow")
  public String ajaxShowTime(Model model) {
    model.addAttribute("timestring", "It is 12:56:59 on Aug 04, 2008.");
    return "ajaxshowtime";
  }

  // private V[] array;
  // public UserAction(int capacity){
  // array = (V[]) new Object[capacity];
  // System.out.print(array);
  // Map<K, V> m = new HashMap<K, V>();
  // }
}

package quanly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import quanly.model.Customer;
import quanly.service.CustomerService;
import quanly.service.CustomerServiceImpl;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "index";
    }

    // them moi ( save )
    @GetMapping("/customer/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        customer.setId((int)(Math.random() * 10000));
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Saved customer successfully!");
        return "redirect:/";
//        Do request gửi lên có HTTP method là POST, nên ta sẽ sử dụng @PostMapping.
//        Đối tượng customer được truyền vào save() chính là đối tượng customer chúng ta đã truyền từ phương thức create()
//        sang view create.html. Đối tượng này sẽ lưu thông tin của người dùng nhập vào.
//        customer.setId() sẽ tạo Id ngẫu nhiên cho đối tượng customer.
//      Sau khi lưu customer, chúng ta sẽ redirect về trang danh sách khách hàng.
//      Chuỗi đằng sau "redirect:" là đường dẫn của trang mà mình muốn redirect.
//      Đồng thời, chúng ta cũng sẽ gửi một Flash message về trang danh sách khách hàng để thông báo lưu thành công,
//      bằng cách sử dụng redirect.addFlashAttribute(messageName, messageContent).
    }

    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit";
//        Tham số @PathVariable int id lấy id của customer từ đường dẫn rồi gán vào biến id.
//        hàm customerService.findById(id) sẽ lấy customer theo id rồi truyền sang view edit.html
    }
    @PostMapping("/customer/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/";
    }
    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "delete";
    }
    @PostMapping("/customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }
}

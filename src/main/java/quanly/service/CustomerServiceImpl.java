package quanly.service;

import quanly.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    private  static  Map<Integer, Customer> customers;

    static {

        customers = new HashMap<>();
        customers.put(1, new Customer(1, "cong", "abc@codegym.vn", "Hanoi"));
        customers.put(2, new Customer(2, "quynh", "cba@codegym.vn", "Danang"));
        customers.put(3, new Customer(3, "dai", "qweqweqwee@codegym.vn", "Saigon"));
        customers.put(4, new Customer(4, "huyen", "adam@codegym.vn", "Beijin"));
        customers.put(5, new Customer(5, "dieu", "123asd@codegym.vn", "Miami"));
        customers.put(6, new Customer(6, "nam", "asdad@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll(){
        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer){

        customers.put(customer.getId(),customer);
    }

    @Override
    public Customer findById(int id){
        return customers.get(id);
    }

    @Override
    public  void update(int id, Customer customer){
        customers.put(id, customer);

    }

    @Override
    public void remove(int id){
        customers.remove(id);
    }
}
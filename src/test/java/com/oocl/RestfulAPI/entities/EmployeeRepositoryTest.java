package com.oocl.RestfulAPI.entities;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestEntityManager manager;

    @After
    public void tearDown()throws Exception{
        manager.clear();
    }

    @Test
    public void should_add_employee_successfully(){
        //given
        Employee employee = new Employee("张三",28,"male",new Company("kuaile"));
        //when
        repository.save(employee);
        //then
        assertThat(repository.findAll().size(),is(1));
    }

    @Test
    public void should_return_all_employees(){
        //given
        manager.persist(new Employee("张三",28,"male",new Company("kuaile")));
        manager.persist(new Employee("Joker",18,"male",new Company("moments")));
        //when
//        List<Employee> employees = repository.findAll(PageRequest.of(0,5)).getContent();
        List<Employee> employees = repository.findAll();

        //then
        System.err.println("654654dfgdf "+employees.size());
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("张三"));
        assertThat(employees.get(0).getAge(), is(28));
    }
    
    @Test
    public void should_return_the_employee_with_id_is_1(){
        //given
        int id = Integer.valueOf(manager.persistAndGetId(new Employee("张三",18,"male",new Company("hah"))).toString());
        manager.persist(new Employee("sisi",18,"female",new Company("hah ")));
        //when
        Employee employee = repository.findById(id).get();
        //then
        //assertThat(employee.getId(),is(1));
        assertThat(employee.getAge(),is(18));
        assertThat(employee.getName(),is("张三"));
        assertThat(employee.getCompany().getName(),is("hah"));
    }

    @Test
    public void should_delete_employee_by_entity_successfully(){
        //given
        manager.persist(new Employee("张三",18,"male",new Company("hah")));
        Employee employee = manager.persistAndFlush(new Employee("sisi",18,"female",new Company("hah ")));
        //when
        repository.delete(employee);
        //then
        assertThat(repository.findAll().size(),is(1));
    }

    @Test
    public void should_delete_employee_by_id_successfully(){
        //given
        //manager.clear();
        int idBefor = Integer.valueOf(manager.persistAndGetId(new Employee("张三",18,"male",new Company("hah"))).toString());
        //when
        Integer id = Integer.valueOf(manager.persistAndGetId(new Employee("sisi",18,"female",new Company("hah "))).toString());
        repository.deleteById(id);
        //then
        assertThat(id,is(idBefor+1));
        assertThat(repository.findAll().size(),is(1));
    }
    
    @Test
    public void should_update_info_successfully(){
        //given
        int id = Integer.valueOf(manager.persistAndGetId(new Employee("张三",18,"male",new Company("hah"))).toString());
        Employee employee = new Employee("Joker",20,"male",new Company("hah"));
        employee.setId(id);
        //when
        repository.save(employee);
        //then
        assertThat(repository.findById(id).get().getName(),is("Joker"));
    }
}
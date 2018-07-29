package com.oocl.RestfulAPI.entities;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager manager;

    @After
    public void tearDown()throws Exception{
        manager.clear();
    }

    @Test
    public void should_return_all_employees(){
        //given
        manager.persist(new Employee("张三",28,"male",new Company("kuaile")));
        manager.persist(new Employee("Joker",18,"male",new Company("moments")));
        //when
        List<Employee> employees = employeeRepository.findAll(PageRequest.of(0,5)).getContent();

        //then
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getName(), is("张三"));
        assertThat(employees.get(0).getAge(), is(28));
    }

}
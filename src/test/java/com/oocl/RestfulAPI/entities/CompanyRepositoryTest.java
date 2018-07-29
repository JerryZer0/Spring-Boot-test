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
public class CompanyRepositoryTest {


    @Autowired
    private CompanyRepository repository;

    @Autowired
    private TestEntityManager manager;

    @After
    public void tearDown() throws Exception {
        manager.clear();
    }

    @Test
    public void should_add_company_successfully() {
        //given
        Company company = new Company("kuaile");
        //when
        repository.save(company);
        //then
        assertThat(repository.findAll().size(), is(1));
    }

    @Test
    public void should_return_all_companies() {
        //given
        manager.persist(new Company("kuaile"));
        manager.persist(new Company("moments"));
        //when
        List<Company> companies = repository.findAll();

        //then
        assertThat(companies.size(), is(2));
        assertThat(companies.get(0).getName(), is("kuaile"));
    }

    @Test
    public void should_return_the_company_with_id_is_1() {
        //given
        int id = Integer.valueOf(manager.persistAndGetId(new Company("hah")).toString());
        manager.persist(new Company("moments"));
        //when
        Company company = repository.findById(id).get();
        //then
        //assertThat(employee.getId(),is(1));
        assertThat(company.getName(), is("hah"));
    }

    @Test
    public void should_delete_company_by_entity_successfully() {
        //given
        manager.persist(new Company("wahah"));
        Company company = manager.persistAndFlush(new Company("hah "));
        //when
        repository.delete(company);
        //then
        assertThat(repository.findAll().size(), is(1));
    }

    @Test
    public void should_delete_company_by_id_successfully() {
        //given
        //manager.clear();
        int idBefor = Integer.valueOf(manager.persistAndGetId(new Company("hah")).toString());
        //when
        Integer id = Integer.valueOf(manager.persistAndGetId(new Company("hah ")).toString());
        repository.deleteById(id);
        //then
        assertThat(id, is(idBefor + 1));
        assertThat(repository.findAll().size(), is(1));
    }

    @Test
    public void should_update_info_successfully() {
        //given
        int id = Integer.valueOf(manager.persistAndGetId(new Company("hah")).toString());
        Company company = new Company("moments");
        company.setId(id);
        //when
        repository.save(company);
        //then
        assertThat(repository.findById(id).get().getName(), is("moments"));
    }
}
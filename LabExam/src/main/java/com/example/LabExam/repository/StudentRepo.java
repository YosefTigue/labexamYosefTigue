package com.example.LabExam.repository;

import com.example.LabExam.model.Student;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class StudentRepo {
    private final JdbcClient jdbcClient;

    public StudentRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Student> findAll(){
        return jdbcClient.sql("Select * from Student")
                .query(Student.class)
                .list();
    }

    public void create(Student student) {
        var updated = jdbcClient.sql("INSERT INTO Student(id,name,email) values(?,?,?)")
                .params(List.of(Student.id(),Student.name(),Student.email().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + Student.name());
    }

}

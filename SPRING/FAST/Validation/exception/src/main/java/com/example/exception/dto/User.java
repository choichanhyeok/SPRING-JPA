package com.example.exception.dto;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    @NotEmpty                   // 비어있으면 안대
    @Size(min = 1, max = 10)    // 최소 한개, 최대 열개
    private String name;

    @Min(1)             // 최소 한개
    @NotNull            // 비어있으면 안돼
    private Integer age;




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
